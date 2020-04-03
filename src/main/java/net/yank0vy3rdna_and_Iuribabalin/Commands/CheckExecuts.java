package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.App.Dispatcher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckExecuts {

    static List<String> files = new ArrayList<>();
    static List<String> iterFiles = new ArrayList<>();


    public String check(String execute, Dispatcher dispatcher) throws IOException {
        StringBuilder builder = new StringBuilder();
        int iter = 0;
        if(execute!= null) {
            while(true) {
                List<String> omg = new ArrayList<>();
                for (String str : execute.split("\n")) {
                    if (str.contains("execute_script")) {
                        omg.add(str);
                    }
                }
                if (omg.size()==0){
                    return execute;
                }
                for (String str : omg) {
                    String file = "";
                    if (checkName(str.split(" ")[1])) {
                        try {
                            file = dispatcher.fileReader.inputCommandFile("resources/" + str.split(" ")[1]);
                        } catch (FileNotFoundException ex) {
                            try {
                                file = dispatcher.fileReader.inputCommandFile("resources/" + str.split(" ")[1] + ".txt");
                            } catch (FileNotFoundException e) {
//                            System.out.println("Такого файла не уществует");
                            }
                        }
                        execute = execute.replace(str, file);
                    } else {
                        execute = execute.replace(str, "");
                    }
                }
            }
        }
        return execute;
    }

    private boolean checkName(String nameFile) {
        for (String name : files) {
            if (nameFile.equals(name))
                return false;
        }
        files.add(nameFile);
        return true;
    }

}
