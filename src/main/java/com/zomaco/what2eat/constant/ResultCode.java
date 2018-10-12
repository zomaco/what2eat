package com.zomaco.what2eat.constant;

public enum ResultCode {
    /**
     * 接口通用返回码
     */
    OK(200),
    SERVER_ERROR(506),
    CLIENT_ERROR(507);

    int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
