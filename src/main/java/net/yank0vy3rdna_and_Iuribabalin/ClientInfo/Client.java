package net.yank0vy3rdna_and_Iuribabalin.ClientInfo;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.App.UI;
import net.yank0vy3rdna_and_Iuribabalin.Commands.OutputCommand;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    public void authorization(UI ui, Dispatcher dispatcher, OutputCommand out) throws NoSuchAlgorithmException, IOException {
        SHA1 sha = new SHA1();
        Socket socket;
        DataOutputStream oos;
        DataInputStream ois;
        String asw = "false";

        while (true){
            try {
                System.out.print("У вас есть учетная запись [Y/N]");
                byte[] outBytes;

                String answ = ui.read().toUpperCase();

                out.setCommand("authorization");



                if(!answ.isEmpty() && !answ.equals("Y") && !answ.equals("N")){
                    ui.print("Введи что-нибудь хорошее=)");
                    continue;
                }

                if (answ.equals("N")) {
                    socket = new Socket("127.0.0.1", 2323);
                    out.setLog(chekSqlIn(ui, false));
                    out.setEmail(chekEmail(ui));
                    outBytes = dispatcher.serialCommand.serializable(out);

                    oos = new DataOutputStream(socket.getOutputStream());
                    ois = new DataInputStream(socket.getInputStream());

                    oos.writeUTF(Arrays.toString(outBytes));
                    oos.flush();

                    byte[] bytes = toByte(ois.readUTF().split(", "));
                    asw = new String(bytes, StandardCharsets.UTF_8);
                    ui.print(asw);
                    ois.close();
                    oos.close();
                    socket.close();
                }
                socket = new Socket("127.0.0.1", 2323);
                String login = chekSqlIn(ui,false);
                out.setLog(login);
                out.setPass(sha.SHA(chekSqlIn(ui, true)));
                dispatcher.login = login;

                dispatcher.sessionID = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
                out.setSessionID(dispatcher.sessionID);

                outBytes = dispatcher.serialCommand.serializable(out);

                oos = new DataOutputStream(socket.getOutputStream());
                ois = new DataInputStream(socket.getInputStream());

                oos.writeUTF(Arrays.toString(outBytes));
                oos.flush();

                byte[] bytes = toByte(ois.readUTF().split(", "));
                asw = new String(bytes, StandardCharsets.UTF_8);
                if(asw.equals("false")) {
                    ui.print("Неправильный логин или пароль");
                    continue;
                }
                dispatcher.owner_id = Long.parseLong(asw);
                ui.print("Вы вошли в систему");
                break;
            }catch (ConnectException ex){
                System.out.println("Server disconnect");
            }
        }
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

    private String chekSqlIn(UI ui, boolean flag){
        String str;
        while(true) {
            if(!flag)
                str = ui.readUserName();
            else
                str = ui.readPassword();
            int n = str.length();
            str = str.replaceAll("[^A-Za-z0-9]", "");
            if (str.length() != n) {
                ui.print("Введён косячный логин");
            }else
                return str;
        }
    }

    private String chekEmail(UI ui){
        while(true) {
            String email = ui.readEmail();
            Pattern p = Pattern.compile("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$");
            Matcher m = p.matcher(email);
            if(m.matches())
                return email;
            ui.print("Email был сожжен на костре инквизиции");
        }
    }

}
