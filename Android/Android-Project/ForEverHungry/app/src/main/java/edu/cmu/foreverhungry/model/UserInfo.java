package edu.cmu.foreverhungry.model;

/**
 * Created by Sudhir Ravi on 11/14/15.
 */
public class UserInfo {
    String email;
    String password;
    boolean loggedIn = false;

    private static UserInfo instance = null;


    private UserInfo() {}

    public static UserInfo getInstance() {
        if(instance == null) {
            instance = new UserInfo();
        }
        return instance;
    }

    public static void clearUserInfo() {
        instance = null;
    }

    public void setEmail( String email){
        this.email = email;
    }

    public void setPassword(String password) {
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

    public String getEmail() {
        return email;
    }


    public boolean authenticate() {
        loggedIn = true;
        // TODO
        return true;
    }
}

