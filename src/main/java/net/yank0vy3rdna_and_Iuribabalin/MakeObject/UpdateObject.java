package net.yank0vy3rdna_and_Iuribabalin.MakeObject;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;
import net.yank0vy3rdna_and_Iuribabalin.Commands.OutputCommand;

import java.io.IOException;

public class UpdateObject implements ObjectExecute {
    @Override
    public void exec(String command, Dispatcher dispatcher, OutputCommand out) throws IOException {
        out.setDragon(dispatcher.reader.create(command.split(" ")[1],dispatcher.owner_id));
    }
}
