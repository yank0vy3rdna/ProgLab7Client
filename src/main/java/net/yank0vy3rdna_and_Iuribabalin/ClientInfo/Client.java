package net.yank0vy3rdna_and_Iuribabalin.ClientInfo;

import net.yank0vy3rdna_and_Iuribabalin.App.UI;

import java.security.NoSuchAlgorithmException;

public class Client {
    private byte[] userName;
    private byte[] passwordSHA;
    private byte[] passwordWord;

    public byte[] getPasswordSHA() {
        return passwordSHA;
    }

    public byte[] getUserName() {
        return userName;
    }

    public byte[] getPasswordWord() {
        return passwordWord;
    }

    public void reg(UI ui) throws NoSuchAlgorithmException {
        SHA1 sha = new SHA1();
        PasswordGenerator gener = new PasswordGenerator();
        userName = sha.SHA(ui.readUserName());
        passwordWord = gener.generator();
        passwordSHA = sha.SHA(passwordWord);
    }

    public void loginer(UI ui) throws NoSuchAlgorithmException {
        SHA1 sha = new SHA1();
        userName = sha.SHA(ui.readUserName());
        passwordSHA = sha.SHA(ui.readPassword());
    }
}
