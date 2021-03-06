package net.yank0vy3rdna_and_Iuribabalin;

import net.yank0vy3rdna_and_Iuribabalin.App.UI;
import net.yank0vy3rdna_and_Iuribabalin.App.App;
import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.Commands.CheckExecuts;
import net.yank0vy3rdna_and_Iuribabalin.Commands.CommandSerializer;
import net.yank0vy3rdna_and_Iuribabalin.Commands.ExecuteScript;
import net.yank0vy3rdna_and_Iuribabalin.Dragon.DragonReader;
import net.yank0vy3rdna_and_Iuribabalin.FileWork.FileReader;
import net.yank0vy3rdna_and_Iuribabalin.MakeObject.AddObject;
import net.yank0vy3rdna_and_Iuribabalin.MakeObject.ObjectExecute;
import net.yank0vy3rdna_and_Iuribabalin.MakeObject.UpdateObject;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Main {
    public static void main( String[] args ) throws IOException, NoSuchAlgorithmException {

        HashMap<String, ObjectExecute> commands = new HashMap<>();
        commands.put("add", new AddObject());
        commands.put("add_if_max", new AddObject());
        commands.put("update", new UpdateObject());
        commands.put("execute_script", new ExecuteScript());

        App app = new App(new UI(), new Dispatcher(commands,new DragonReader(), new CommandSerializer(), new FileReader(), new CheckExecuts()));
        try {
            app.start();
        }catch (NoSuchElementException ex){
            System.out.println("Выйди нормально разбийник");
        }
    }
}