package com.vaskka.scu.facade.utils;

import org.slf4j.Logger;
import org.slf4j.helpers.MessageFormatter;

/**
 * @author rusheng
 */
public class LogUtils {

    /**
     * debug 日志
     * @param logger logger
     * @param format format str
     * @param args 参数
     */
    public static void debugf(Logger logger, String format, Object ...args) {
        logger.debug(format, args);
    }

    /**
     * info 日志
     * @param logger logger
     * @param format format str
     * @param args 参数
     */
    public static void infof(Logger logger, String format, Object ...args) {
        logger.info(format, args);
    }

    /**
     * error 日志
     * @param logger logger
     * @param format format str
     * @param args 参数
     */
    public static void errorf(Logger logger, String format, Object ...args) {
        logger.error(format, args);
    }

    /**
     * error 日志
     * @param logger logger
     * @param cause Throwable cause
     * @param format format str
     * @param args 参数
     */
    public static void errorf(Logger logger, Throwable cause, String format,  Object... args) {
        var formattingTuple = MessageFormatter.format(format, args);
        logger.error(formattingTuple.getMessage(), cause);
    }

}
