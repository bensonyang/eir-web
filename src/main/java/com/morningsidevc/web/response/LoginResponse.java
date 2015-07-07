package com.morningsidevc.web.response;

/**
 * @author float.lu
 */
public class LoginResponse {
    private int userId;
    private String account;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
