package com.yitutech.common.result;

import static com.yitutech.common.error.CodeUtil.getErrorMsg;

import com.yitutech.common.error.CommonCode;

/**
 * Created by Easy
 */
public class ResultFactory {
    
    public static Result err(int errorCode) {
        return Result.builder()
            .code(errorCode)
            .message(getErrorMsg(errorCode))
            .build();
    }


    public static Result ok() {
        return Result.builder()
            .code(CommonCode.OK)
            .message(getErrorMsg(CommonCode.OK))
            .build();
    }

    public static Result ok(Object data) {
        return Result.builder()
            .code(CommonCode.OK)
            .message(getErrorMsg(CommonCode.OK))
            .data(data)
            .build();
    }
}
