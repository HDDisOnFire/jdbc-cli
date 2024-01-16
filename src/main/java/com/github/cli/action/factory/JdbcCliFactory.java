package com.github.cli.action.factory;

import com.github.cli.action.CliClientAction;
import com.github.cli.action.mysql.MySQLCliAction;

/**
 * 客户端工厂
 */
public class JdbcCliFactory {

    /**
     * 暂时只有一种
     *
     * @return
     */
    public static CliClientAction create() {
        return new MySQLCliAction();
    }
}
