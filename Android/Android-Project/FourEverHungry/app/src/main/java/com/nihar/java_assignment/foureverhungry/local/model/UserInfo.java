package com.nihar.java_assignment.foureverhungry.local.model;

/**
 * Created by Sudhir Ravi on 11/14/15.
 */
public class UserInfo {
    String email;
    String password;
    boolean loggedIn = false;

    public UserInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean authenticate() {
        loggedIn = true;
        // TODO
        return true;
    }
}
