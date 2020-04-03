package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.MakeObject.ObjectExecute;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExecuteScript implements ObjectExecute {
    public void exec(String command, Dispatcher dispatcher) throws IOException {
        try {
            dispatcher.out.setExecute_commands(dispatcher.fileReader.inputCommandFile("resources/" + command.split(" ")[1]));
        }catch (FileNotFoundException ex){
            try {
                dispatcher.out.setExecute_commands(dispatcher.fileReader.inputCommandFile("resources/" + command.split(" ")[1] + ".txt"));
            }catch (FileNotFoundException e) {
                System.out.println("Такого файла не уществует");
            }
        }
    }
}
