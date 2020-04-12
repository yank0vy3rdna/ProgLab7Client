package net.yank0vy3rdna_and_Iuribabalin.MakeObject;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.Commands.OutputCommand;

import java.io.IOException;

public class AddObject implements ObjectExecute {

    public void exec(String command, Dispatcher dispatcher, OutputCommand out) throws IOException {
        out.setDragon(dispatcher.reader.create("null"));
    }
}
