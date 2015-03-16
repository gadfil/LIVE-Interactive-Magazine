package com.aod.clubapp.api.model.auth;

/**
 * Created by gadfil on 13.11.2014.
 */
public class SignUpRequest {
    private String email;
    private String password;
    private String login;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
