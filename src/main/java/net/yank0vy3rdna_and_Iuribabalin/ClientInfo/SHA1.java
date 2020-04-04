package net.yank0vy3rdna_and_Iuribabalin.ClientInfo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 {
    public byte[] SHA(byte[] password) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        return sha1.digest(password);
    }
}
