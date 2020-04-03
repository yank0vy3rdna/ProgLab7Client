package net.yank0vy3rdna_and_Iuribabalin.MakeObject;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;

import java.io.IOException;

public class AddObjact implements ObjectExecute {

    public void exec(String command, Dispatcher dispatcher) throws IOException {
        dispatcher.out.setDragon(dispatcher.reader.create("null"));
    }
}
