package com.ringcenter.sdkdemo.enums;

/**
 * @author Xu Dongdong
 * @date 2020-5-1
 */
public enum  ErrorEnum {

    SAVE_FAIL(99,""),

    NO_FILE(99,""),

    UPDATE_LIMITED(99,"");

    private int bzCode;
    private String msg;

    private ErrorEnum(int bzCode, String msg) {
        this.bzCode = bzCode;
        this.msg = msg;
    }

    public int getBzCode() {
        return bzCode;
    }

    public void setBzCode(int bzCode) {
        this.bzCode = bzCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
