package net.yank0vy3rdna_and_Iuribabalin;

import net.yank0vy3rdna_and_Iuribabalin.App.UI;
import net.yank0vy3rdna_and_Iuribabalin.App.App;
import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.Commands.CheckExecuts;
import net.yank0vy3rdna_and_Iuribabalin.Commands.CommandSerializable;
import net.yank0vy3rdna_and_Iuribabalin.Commands.ExecuteScript;
import net.yank0vy3rdna_and_Iuribabalin.Commands.OutputCommand;
import net.yank0vy3rdna_and_Iuribabalin.Dragon.DragonReader;
import net.yank0vy3rdna_and_Iuribabalin.FileWork.FileReader;
import net.yank0vy3rdna_and_Iuribabalin.MakeObject.AddObjact;
import net.yank0vy3rdna_and_Iuribabalin.MakeObject.ObjectExecute;
import net.yank0vy3rdna_and_Iuribabalin.MakeObject.UpdateObject;

import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main( String[] args ) throws IOException {

        HashMap<String, ObjectExecute> commands = new HashMap<>();
        commands.put("add", new AddObjact());
        commands.put("add_if_max", new AddObjact());
        commands.put("update", new UpdateObject());
        commands.put("execute_script", new ExecuteScript());

        App app = new App(new UI(), new Dispatcher(commands, new DragonReader(), new CommandSerializable(), new FileReader(), new OutputCommand(), new CheckExecuts()));

        app.start();
    }
}