package com.github.cli.factory;

import com.github.cli.action.CliClient;
import com.github.cli.mysql.MySQLCli;

/**
 * 客户端工厂
 */
public class JdbcCliFactory {

    /**
     * 暂时只有一种
     *
     * @return
     */
    public static CliClient create() {
        return new MySQLCli();
    }
}
