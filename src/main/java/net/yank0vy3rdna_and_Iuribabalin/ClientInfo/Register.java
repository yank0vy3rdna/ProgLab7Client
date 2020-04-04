package net.yank0vy3rdna_and_Iuribabalin.ClientInfo;

import net.yank0vy3rdna_and_Iuribabalin.App.UI;

import java.security.NoSuchAlgorithmException;

public class Register {
    private byte[] userName;
    private byte[] password;

    public byte[] getPassword() {
        return password;
    }

    public byte[] getUserName() {
        return userName;
    }

    public void reg(UI ui) throws NoSuchAlgorithmException {
        SHA1 sha = new SHA1();
        userName = sha.PasswordSHA1(ui.readUserName());
        password = sha.PasswordSHA1(ui.readPassword());
    }
}
