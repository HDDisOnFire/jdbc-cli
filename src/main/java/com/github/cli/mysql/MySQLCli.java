package com.github.cli.mysql;

import com.github.cli.action.CliClient;
import com.github.cli.constant.CliConstant;
import com.github.cli.reader.CommandReader;
import com.github.cli.reader.CommandReaderFactory;
import com.github.cli.util.CloseUtil;
import com.github.cli.util.ResultSetPrinter;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

@Data
@Slf4j
public class MySQLCli implements CliClient {

    private String jdbcUrl;

    private String username;

    private String password;

    private DataSource dataSource;
    /**
     * 行扫描器
     */
    private CommandReader reader;


    public MySQLCli() {
        this.reader = CommandReaderFactory.getReader();
    }

    @Override
    public void handleCommand() {
        try {
            log.info("即将开始输入查询命令");
            // 输入命令
            for (String line = this.getInputCommand(); canExecute(line); line = this.getInputCommand()) {
                this.executeSql(line);
            }
        } finally {
            this.reader.close();
        }
    }

    /**
     * 获取输入的指令
     *
     * @return
     */
    private String getInputCommand() {
        log.info("请输入查询语句：");
        return this.reader.read();
    }

    /**
     * 执行sql
     *
     * @param sql
     */
    private void executeSql(String sql) {
        if (StringUtils.isEmpty(sql)) {
            return;
        }
        sql = sql.trim();
        if (!sql.toLowerCase(Locale.ENGLISH).startsWith("select")) {
            log.warn("目前只能执行查询语句!");
            return;
        }
        log.info("开始执行sql:{}", sql);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            ResultSetPrinter.print(resultSet);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            CloseUtil.close(resultSet, statement, connection);
        }
    }

    /**
     * 判断是否可以执行
     *
     * @param command
     * @return
     */
    private boolean canExecute(String command) {
        return !StringUtils.equalsIgnoreCase(CliConstant.EXIT_COMMAND, command);
    }

    /**
     * 初始化参数
     */
    @Override
    public void initParams() {
        // 输入jdbcUrl
        log.info("请输入JdbcUrl:");
        this.jdbcUrl = this.reader.read();
        this.jdbcUrl = this.jdbcUrl.trim();
        log.info("你输入的jdbcUrl是:{}", this.jdbcUrl);
        // 输入用户名
        log.info("请输入username:");
        this.username = this.reader.read();
        this.username = this.username.trim();
        log.info("你输入的username是:{}", this.username);
        // 输入密码
        log.info("请输入password:");
        this.password = this.reader.read();
        this.password = this.password.trim();
        log.info("你输入的username是:{}", this.password);
    }

    /**
     * 构建连接池
     */
    @Override
    public void buildDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(this.jdbcUrl);
        config.setUsername(this.username);
        config.setPassword(this.password);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setPoolName("MySQL-Cli-Hikari-CP");
        config.setMaximumPoolSize(10);
        this.dataSource = new HikariDataSource(config);
        log.info("构建数据连接池成功");
    }

    private Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

}
