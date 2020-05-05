package net.yank0vy3rdna_and_Iuribabalin.App;

import net.yank0vy3rdna_and_Iuribabalin.ClientInfo.Client;
import net.yank0vy3rdna_and_Iuribabalin.Commands.CheckExecuts;

import net.yank0vy3rdna_and_Iuribabalin.Commands.CommandSerializer;
import net.yank0vy3rdna_and_Iuribabalin.Commands.OutputCommand;
import net.yank0vy3rdna_and_Iuribabalin.Dragon.DragonReader;
import net.yank0vy3rdna_and_Iuribabalin.FileWork.FileReader;
import net.yank0vy3rdna_and_Iuribabalin.MakeObject.ObjectExecute;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

public class Dispatcher {
    HashMap<String, ObjectExecute> commands;
    public DragonReader reader;
    public CommandSerializer serialCommand;
    public FileReader fileReader;
    public CheckExecuts check;
    public long sessionID = 0;
    public long owner_id;
    public String login;

    public Dispatcher(HashMap<String, ObjectExecute> commands, DragonReader reder, CommandSerializer serialCommand,
                      FileReader fileReader, CheckExecuts check){
        this.reader = reder;
        this.commands = commands;
        this.serialCommand = serialCommand;
        this.fileReader = fileReader;
        this.check = check;
    }

    public String dispatch(String clientCommand, Socket socket, App app,OutputCommand out) throws IOException {
        reader.setUI(new UI());

        out.setSessionID(sessionID);

        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream());
        String[] splitted = clientCommand.split(" ");
        out.setCommand(splitted[0]);
        String[] args = new String[splitted.length-1];
        System.arraycopy(splitted,1,args,0,splitted.length-1);
        out.setArgs(args);
        try {
            byte[] outBytes;

            if (commands.get(clientCommand.split(" ")[0].toLowerCase()) != null) {

                ObjectExecute doComm =  commands.get(clientCommand.split(" ")[0]);
                doComm.exec(clientCommand,this, out);
                out.setLog(login);
                out.setExecute_commands(check.check(out.getExecute_commands(), this));

                System.out.println(out.getExecute_commands());

                outBytes = serialCommand.serializable(out);
                oos.writeUTF(Arrays.toString(outBytes));
                oos.flush();

            }else if(clientCommand.equals("exit")){
                outBytes = serialCommand.serializable(out);
                oos.writeUTF(Arrays.toString(outBytes));
                oos.flush();

                app.stopWork();

                byte[] bytes = toByte(ois.readUTF().split(", "));
                String asw = new String(bytes, StandardCharsets.UTF_8);

                return asw;
            }
            else{
                outBytes = serialCommand.serializable(out);
                oos.writeUTF(Arrays.toString(outBytes));
                oos.flush();
            }
        }catch (NullPointerException ex){
            app.stopWork();

            return "Client off work";
        }

        byte[] bytes = toByte(ois.readUTF().split(", "));
        String asw = new String(bytes, StandardCharsets.UTF_8);

        for(String s: asw.split("\n")){
            if(s.trim().equals("Работа в консоли закончена")){
                app.stopWork();
                return asw;
            }
        }

        return asw;
    }



    private byte[] toByte(String[] str){
        byte[] bytes = new byte[str.length];
        String s = "-";

        if(str[0].split("-").length == 2){
            s += str[0].split("-")[1];
            str[0] = s;
        }else{
            str[0] = str[0].replaceAll("[^0-9]", "");
        }

        str[str.length - 1] = str[str.length - 1].split("]")[0];

        for(int i = 0;i< str.length;i++){
            bytes[i] = Byte.parseByte(str[i]);
        }
        return bytes;
    }
}