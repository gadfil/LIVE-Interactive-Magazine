package com.aod.clubapp.api.model.auth;

/**
 * Created by gadfil on 13.11.2014.
 */
public class SignRequest {
    private String login;
    private String password;

    public SignRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public SignRequest() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignRequest{" +
                "\nlogin='" + login + '\'' +
                ", \npassword='" + password + '\'' +
                '}';
    }
}
