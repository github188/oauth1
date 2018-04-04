package com.yitutech.common.exception;

/**
 * Created by Easy
 *
 * 胸部CT业务异常累
 */
public class ChestCTException extends Exception {

    /**
     * 错误码
     */
    private int errorCode;

    /**
     * 错误码描述
     */
    private String errorMsg;


    public ChestCTException(int errorCode, String errorMgs) {
        this.errorCode = errorCode;
        this.errorMsg = errorMgs;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
