package com.glslx.jdbc.cli.util;

import com.glslx.jdbc.cli.constant.CliConstant;
import com.glslx.jdbc.cli.exception.ExitCliException;
import org.apache.commons.lang3.StringUtils;

public class CliExitUtil {
    /**
     * 判断是否退出
     *
     * @param input
     */
    public static void checkExitCli(String input) {
        if (StringUtils.isEmpty(input)) {
            return;
        }
        if (StringUtils.equalsIgnoreCase(CliConstant.EXIT_COMMAND, input.trim())) {
            throw new ExitCliException("主动退出应用，再见！");
        }
    }
}
