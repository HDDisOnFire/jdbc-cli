package com.glslx.jdbc.cli.reader;

public interface CommandReader {
    /**
     * 读取指令
     *
     * @return
     */
    String read();

    /**
     * 关闭读取器
     */
    default void close() {

    }
}
