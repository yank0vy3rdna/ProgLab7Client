package net.yank0vy3rdna_and_Iuribabalin.ClientInfo;

import net.yank0vy3rdna_and_Iuribabalin.App.UI;

import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class Client {
    private byte[] pass;
    private byte[] name;

    public byte[] getName() {
        return name;
    }

    public byte[] getPass() {
        return pass;
    }

    public void authorization(UI ui) throws NoSuchAlgorithmException {
        SHA1 sha = new SHA1();
        ui.print("У вас есть учетная запись\nYes    No");
        while (true){
            String answ = ui.read();
            if(answ.equals("YES")){
                name = sha.SHA(ui.readUserName());
                pass = sha.SHA(ui.readPassword());
                break;
            }else if(answ.equals("NO")){
                name = sha.SHA(ui.readUserName());
                break;
            }else{
                ui.print("Введите корректно Yes или No");
            }
        }
    }
}
