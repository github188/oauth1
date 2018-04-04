package com.yitutech.common.error;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by Easy
 */
public class CodeUtil {

    /**
     * 格式化返回信息
     *
     * @param errorCode　error code
     * @param debugMsg　debug message
     * @return
     */
    public static String getFormatMsg(int errorCode, String debugMsg) {
        String errorMgs = getErrorMsg(errorCode,null);
        return getFormatMsg(errorCode, errorMgs, debugMsg);
    }


    /**
     * 格式化返回信息
     *
     * @param errorCode　error code
     * @param errorMgs error message
     * @param debugMsg debug message
     * @return
     */
    public static String getFormatMsg(int errorCode, String errorMgs, String debugMsg) {
        StringBuilder message = new StringBuilder();
        message
            .append("[")
            .append(errorCode)
            .append("]-[");

        if (!StringUtils.isEmpty(errorMgs)) {
            message.append(errorMgs);
        }
        message.append("]");

        if (StringUtils.isNotEmpty(debugMsg)) {
            message.append(":").append(debugMsg);
        }
        return message.toString();
    }


    /**
     * 根据errorCode获取对应error_code.properties中的错误描述信息
     *
     * @param errorCode　error　code
     * @param defaultValue 默认返回值
     * @return
     */
    public static String getErrorMsg(int errorCode, String defaultValue) {
        if(StringUtils.isNotEmpty(defaultValue)) {
            return defaultValue;
        }
        return CodeConfig.getCodeConfig().getMessage(errorCode, defaultValue);
    }

    /**
     * 根据errorCode获取对应error_code.properties中的错误描述信息
     *
     * @param errorCode　error　code
     * @return
     */
    public static String getErrorMsg(int errorCode) {
        return getErrorMsg(errorCode, null);
    }
}
