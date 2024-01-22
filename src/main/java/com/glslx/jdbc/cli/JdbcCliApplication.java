package com.glslx.jdbc.cli;

import com.glslx.jdbc.cli.action.CliClientAction;
import com.glslx.jdbc.cli.exception.ExitCliException;
import com.glslx.jdbc.cli.exception.JdbcCliException;
import com.glslx.jdbc.cli.action.factory.JdbcCliFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 创建一个初始化的命令应用，方便明天现场调试界面
 */
@Slf4j
public class JdbcCliApplication {

    public static void main(String[] args) {
        try {
            log.info("Hello,Jdbc Cli Application!");
            CliClientAction cliClient = JdbcCliFactory.create();
            cliClient.start();
        } catch (ExitCliException e) {
            log.info(e.getMessage());
        } catch (JdbcCliException e) {
            log.error(e.getMessage(), e);
            log.error("构建应用失败，即将退出，再见!");
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            log.error("构建应用失败，即将退出，再见!");
        }
    }
}
