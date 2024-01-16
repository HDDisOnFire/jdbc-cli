package com.github.cli.action;

/**
 * 行为定义
 */
public interface CliClient {
    /***
     * 启动客户端
     */
    default void start() {
        this.initParams();
        this.buildDataSource();
        this.handleCommand();
    }

    /**
     * 初始化参数
     */
    void initParams();

    /**
     * 构建数据源
     */
    void buildDataSource();

    /**
     * 执行指令
     */
    void handleCommand();
}
