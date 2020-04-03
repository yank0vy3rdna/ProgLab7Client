package net.yank0vy3rdna_and_Iuribabalin.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class UI {
    private Scanner in;
    private boolean prints;
    public void setScanner(Scanner scanner){
        in = scanner;
    }

    public void setPrints(boolean prints) {
        this.prints = prints;
    }

    public UI(){
        in = new Scanner(System.in);
        prints = true;
    }

    public String getNextCommand(BufferedReader br) throws IOException {
        if (prints) {
            System.out.print(">> ");
        }
        return br.readLine();
    }

    public String readField(String fieldName){
        if (prints) {
            System.out.print("Введите значение поля " + fieldName + ": ");
        }
        return in.nextLine().toUpperCase();
    }

    public void print(String string){
        if(string == null)
            System.out.print("");
        else
            System.out.println(string);
    }

}
