package com.primerworldapps.pointer.network.response;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 11.02.14
 * Time: 23:28
 * To change this template use File | Settings | File Templates.
 */
public class BaseResponse {
    public static final int CODE_SUCCESS = 0;

    protected int code;
    protected String errorMessage;

    public boolean isRequestSucceed() {
        return (code == CODE_SUCCESS);
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
