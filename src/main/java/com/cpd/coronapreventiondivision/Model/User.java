package com.cpd.coronapreventiondivision.Model;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class User {

    private String username;
    private String password;
    private UserType level;

    public enum UserType {
        ADMIN,
        SECRETARY
    }

    public User(String username, String password, UserType level) {
        this.username = username;
        this.password = hash(password);
        this.level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level +
                '}';
    }

    public static String hash(String input){
        return Hashing.sha256().hashString(input, StandardCharsets.UTF_8).toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getLevel() {
        return level;
    }

    public void setLevel(UserType level) {
        this.level = level;
    }
}
