package com.dxs.common.enums;

/**
 * 响应码枚举
 * @className  ResultCodeEnum
 * @author  Mr.Xiong
 * @date  2022/04/09
 */
public enum ResultCodeEnum {
    // 成功
    OK(200, "SUCCESS", "成功"),
    // 失败
    FAIL(201, "FAIL", "失败"),
    // 刷新token失效
    REFRESH_TOKEN_INVALIDATION(205, "refresh token is invalid", "刷新token失效");

    private final int status;
    private final String message;
    private final String alias;

    ResultCodeEnum(int status, String message, String alias) {
        this.status = status;
        this.message = message;
        this.alias = alias;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getAlias() {
        return alias;
    }
}
