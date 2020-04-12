package net.yank0vy3rdna_and_Iuribabalin.ClientInfo;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.App.UI;
import net.yank0vy3rdna_and_Iuribabalin.Commands.OutputCommand;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;

public class Client {
    public void authorization(UI ui, Dispatcher dispatcher, OutputCommand out) throws NoSuchAlgorithmException, IOException {
        SHA1 sha = new SHA1();
        Socket socket;
        DataOutputStream oos;
        DataInputStream ois;

        while (true){
            try {
                ui.print("У вас есть учетная запись [Y/N]");

                byte[] outBytes;
                byte[] sizeBytes;
                String answ = ui.read().toUpperCase();

                out.setCommand("authorization");

                socket = new Socket("127.0.0.1", 2323);
                if(!answ.isEmpty() && !answ.equals("Y") && !answ.equals("N"))
                    continue;

                if (answ.equals("N")) {
                    out.setLog(ui.readUserName());
                    out.setEmail(ui.readEmail());

                    oos = new DataOutputStream(socket.getOutputStream());
                    ois = new DataInputStream(socket.getInputStream());

                    outBytes = dispatcher.serialCommand.serializable(out);
                    sizeBytes = ByteBuffer.allocate(4).putInt(outBytes.length).array();

                    oos.write(sizeBytes);
                    oos.write(outBytes);
                    oos.flush();

                    ui.print(ois.readUTF());
                }

                out.setLog(ui.readUserName());
                out.setPass(sha.SHA(ui.readPassword()));

                dispatcher.setLog(out.getLog());
                dispatcher.setPass(out.getPass());

                oos = new DataOutputStream(socket.getOutputStream());
                ois = new DataInputStream(socket.getInputStream());

                outBytes = dispatcher.serialCommand.serializable(out);
                sizeBytes = ByteBuffer.allocate(4).putInt(outBytes.length).array();

                oos.write(sizeBytes);
                oos.write(outBytes);
                oos.flush();

                ui.print(ois.readUTF());
                break;
            }catch (ConnectException ex){
                System.out.println("Server disconnect");
            }
        }
    }
}
