package com.cpd.coronapreventiondivision.Model;

public class User {

    private String username;
    private String password;
    private UserType level;

    public enum UserType {
        ADMIN,
        SECRETARY
    }

    public User() {}

    public User(String username, String password, UserType level) {
        this.username = username;
        this.password = password;
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
