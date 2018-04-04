package com.yitutech.common.log;

import com.yitutech.common.error.CodeUtil;
import org.slf4j.Logger;

/**
 * Created by Easy
 */

public class MedicalLog{

    private Logger logger;

    public MedicalLog(Logger logger) {
        super();
        this.logger = logger;
    }

    public String getName() {
        return logger.getName();
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public void debug(String msg) {
        logger.debug(msg);
    }

    public void debug(String msg, Throwable t) {
        logger.debug(msg, t);
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void info(String msg, Throwable t) {
        logger.info(msg, t);
    }

    public void info(String var1, Object var2) {
        logger.info(var1, var2);
    }

    public void info(String var1, Object var2, Object var3) {
        logger.info(var1, var2, var3);
    }


    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    public void warn(String msg) {
        logger.warn(msg);
    }

    public void warn(String msg, Throwable t) {
        logger.warn(msg, t);
    }

    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    public void error(String msg) {
        logger.error(msg);
    }

    public void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    public void debug(int errCode, String msg) {
        if (isDebugEnabled()) {
            debug(CodeUtil.getFormatMsg(errCode, msg));
        }
    }

    public void debug(int errCode, String msg, Throwable t) {
        if (isDebugEnabled()) {
            debug(CodeUtil.getFormatMsg(errCode, msg), t);
        }
    }

    public void info(int errCode, String msg) {
        if (isInfoEnabled()) {
            info(CodeUtil.getFormatMsg(errCode, msg));
        }
    }

    public void info(int errCode, String msg, Throwable t) {
        if (isInfoEnabled()) {
            info(CodeUtil.getFormatMsg(errCode, msg), t);
        }
    }

    public void warn(int errCode, String msg) {
        if (isWarnEnabled()) {
            warn(CodeUtil.getFormatMsg(errCode, msg));
        }
    }

    public void warn(int errCode, String msg, Throwable t) {
        if (isWarnEnabled()) {
            warn(CodeUtil.getFormatMsg(errCode, msg), t);
        }
    }

    public void error(int errCode, String msg) {
        error(CodeUtil.getFormatMsg(errCode, msg));
    }

    public void error(int errCode, Throwable t) {
        error(CodeUtil.getFormatMsg(errCode, null), t);
    }


    public void error(int errCode, String msg, Throwable t) {
        error(CodeUtil.getFormatMsg(errCode, msg), t);
    }

}
