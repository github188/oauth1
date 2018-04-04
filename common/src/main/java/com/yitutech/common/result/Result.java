package com.yitutech.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Easy
 */
@Data
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Result {

    /**
     * code
     */
    private int code;

    /**
     * error message
     */
    private String message;

    /**
     * return data object
     */
    private Object data;
}
