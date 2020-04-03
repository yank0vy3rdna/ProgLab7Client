package net.yank0vy3rdna_and_Iuribabalin.App;

import net.yank0vy3rdna_and_Iuribabalin.Commands.CheckExecuts;
import net.yank0vy3rdna_and_Iuribabalin.Commands.CommandSerializable;
import net.yank0vy3rdna_and_Iuribabalin.Commands.OutputCommand;
import net.yank0vy3rdna_and_Iuribabalin.Dragon.DragonReader;
import net.yank0vy3rdna_and_Iuribabalin.FileWork.FileReader;
import net.yank0vy3rdna_and_Iuribabalin.MakeObject.ObjectExecute;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Dispatcher {
    HashMap<String, ObjectExecute> commands;
    public DragonReader reader;
    public CommandSerializable serialCommand;
    public FileReader fileReader;
    public OutputCommand out;
    public CheckExecuts check;


    public Dispatcher(HashMap<String, ObjectExecute> commands, DragonReader reder, CommandSerializable serialCommand,
                      FileReader fileReader, OutputCommand out, CheckExecuts check){
        this.reader = reder;
        this.commands = commands;
        this.serialCommand = serialCommand;
        this.out = out;
        this.fileReader = fileReader;
        this.check = check;
    }

    public String dispatch(String clientCommand, Socket socket, App app) throws IOException {
        reader.setUI(new UI());

        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream());
        String[] splitted = clientCommand.split(" ");
        out.setCommand(splitted[0]);
        String[] args = new String[splitted.length-1];
        System.arraycopy(splitted,1,args,0,splitted.length-1);
        out.setArgs(args);
        try {
            byte[] outBytes;
            byte[] sizeBytes;

            if (commands.get(clientCommand.split(" ")[0].toLowerCase()) != null) {

                ObjectExecute doComm =  commands.get(clientCommand.split(" ")[0]);
                doComm.exec(clientCommand,this);

                out.setExecute_commands(check.check(out.getExecute_commands(), this));

                System.out.println(out.getExecute_commands());

                outBytes = serialCommand.serializable(out);
                sizeBytes = ByteBuffer.allocate(4).putInt(outBytes.length).array();

                oos.write(sizeBytes);
                oos.write(outBytes);
                oos.flush();

            }else if(clientCommand.equals("exit")){
                outBytes = serialCommand.serializable(out);
                sizeBytes = ByteBuffer.allocate(4).putInt(outBytes.length).array();

                oos.write(sizeBytes);
                oos.write(outBytes);
                oos.flush();

                app.stopWork();

                return ois.readUTF();
            }
            else{
                outBytes = serialCommand.serializable(out);
                oos.write(outBytes);
                oos.flush();
            }
        }catch (NullPointerException ex){
            app.stopWork();

            return "Client off work";
        }

        while (ois.available()==0){}

        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[ois.available()];
        inputStream.read(bytes, 0, ois.available());
        String asw = new String(bytes, StandardCharsets.UTF_8);

        for(String s: asw.split("\n")){
            if(s.trim().equals("Работа в консоли закончена")){
                app.stopWork();
                return asw;
            }
        }

        return asw;
    }
}
