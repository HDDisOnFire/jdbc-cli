package com.github.cli.reader;

public class CommandReaderFactory {

    public static final CommandReader getReader() {
        return new ConsoleReader();
    }
}
