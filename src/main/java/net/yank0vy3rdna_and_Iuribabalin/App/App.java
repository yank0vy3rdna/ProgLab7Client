package net.yank0vy3rdna_and_Iuribabalin.App;

import net.yank0vy3rdna_and_Iuribabalin.ClientInfo.Client;
import net.yank0vy3rdna_and_Iuribabalin.Commands.OutputCommand;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class App {

    private final UI ui;
    private final Dispatcher dispatcher;
    private boolean flag = true;

    public App(UI ui, Dispatcher dispatcher){
        this.ui = ui;
        this.dispatcher = dispatcher;
    }

    public void start() throws IOException, NoSuchAlgorithmException {
        Client client = new Client();
        client.authorization(new UI(), dispatcher, new OutputCommand());
        while(flag) {
            OutputCommand out = new OutputCommand();
            Socket socket = new Socket();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String command = ui.getNextCommand(br);
                if(command == null){
                    break;
                }
                socket = new Socket("127.0.0.1", 2323);
                String answ = dispatcher.dispatch(command,socket, this, out);
                if (answ.equals(">>")){
                    socket.close();
                    continue;
                }
                ui.print(answ);

                socket.close();

            }catch (ConnectException ex){
                ui.print("Server disconnect");
            }catch (EOFException ignored){
            }catch (NullPointerException ex){
                socket.close();
                flag = false;
                break;
            }

        }
    }

    public void stopWork(){
        this.flag = false;
    }

}