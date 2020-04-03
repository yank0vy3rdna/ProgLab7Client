package net.yank0vy3rdna_and_Iuribabalin.Commands;

import net.yank0vy3rdna_and_Iuribabalin.Dragon.Dragon;

import java.io.Serializable;
import java.util.Scanner;

public class OutputCommand implements Serializable {
    private String command = null;

    private String[] args = null;

    private Scanner scanner = null;

    private Dragon dragon = null;

    private String execute_commands = null;

    public void setCommand(String command) {
        this.command = command;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }

    public void setExecute_commands(String execute_commands) {
        this.execute_commands = execute_commands;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public String getExecute_commands() {
        return execute_commands;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}

