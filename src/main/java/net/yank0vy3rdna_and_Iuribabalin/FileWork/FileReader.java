package net.yank0vy3rdna_and_Iuribabalin.FileWork;

import java.io.*;

public class FileReader {

    public String inputCommandFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader((new InputStreamReader(new FileInputStream(filename))));
        char[] charBuffer = new char[8 * 1024];
        StringBuilder builder = new StringBuilder();
        int numCharsRead;
        while ((numCharsRead = reader.read(charBuffer, 0, charBuffer.length)) != -1) {
            builder.append(charBuffer, 0, numCharsRead);
        }
        return String.valueOf(builder);
    }
}
