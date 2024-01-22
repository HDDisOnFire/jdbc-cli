package com.glslx.jdbc.cli.exception;

public class JdbcCliException extends RuntimeException {
    public JdbcCliException() {

    }

    public JdbcCliException(String message) {
        super(message);
    }

    public JdbcCliException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public JdbcCliException(Throwable throwable) {
        super(throwable);
    }
}
