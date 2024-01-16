package com.github.cli.reader;

import com.github.cli.util.CliExitUtil;
import com.github.cli.util.CloseUtil;

import java.util.Scanner;

public class ConsoleReader implements CommandReader {

    private Scanner scanner;

    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String read() {
        String line = this.scanner.nextLine();
        CliExitUtil.checkExitCli(line);
        return line;
    }

    @Override
    public void close() {
        CloseUtil.close(this.scanner);
    }
}
