package com.glslx.jdbc.cli.util;

import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.util.Objects;

@Slf4j
public class CloseUtil {

    public static void close(AutoCloseable closeable) {
        try {
            if (Objects.isNull(closeable)) {
                return;
            }
            closeable.close();
        } catch (Exception e) {

        }
    }

    public static void close(AutoCloseable... closeable) {
        if (Objects.isNull(closeable)) {
            return;
        }
        for (AutoCloseable c : closeable) {
            close(c);
        }
    }

    public static void close(Closeable closeable) {
        try {
            if (Objects.isNull(closeable)) {
                return;
            }
            closeable.close();
        } catch (Exception e) {

        }
    }

    public static void close(Closeable... closeable) {
        if (Objects.isNull(closeable)) {
            return;
        }
        for (Closeable c : closeable) {
            close(c);
        }
    }
}
