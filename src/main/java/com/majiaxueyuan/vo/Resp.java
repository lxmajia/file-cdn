package com.majiaxueyuan.vo;

/**
 * @author Liao
 */
public class Resp {
    private RespContent content;

    private Boolean isSuccess;
    private String msg;

    public RespContent getContent() {
        return content;
    }

    public void setContent(RespContent content) {
        this.content = content;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
