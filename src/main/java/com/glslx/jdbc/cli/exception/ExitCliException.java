package com.glslx.jdbc.cli.exception;

/**
 * 主动推出的异常
 */
public class ExitCliException extends JdbcCliException{
    public ExitCliException() {

    }

    public ExitCliException(String message) {
        super(message);
    }

    public ExitCliException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ExitCliException(Throwable throwable) {
        super(throwable);
    }
}
