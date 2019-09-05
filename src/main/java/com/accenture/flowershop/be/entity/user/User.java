package com.accenture.flowershop.be.entity.user;

import java.time.LocalDate;

public class User {
    private String login;
    private String password;
//    private LocalDate localDate;

    public User(String name, String passwords) {
        this.login = name;
        this.password = passwords;
//        this.localDate = localDate;
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

//    public LocalDate getLocalDate() {
//        return localDate;
//    }
//
//    public void setLocalDate(LocalDate localDate) {
//        this.localDate = localDate;
//    }


    @Override
    public boolean equals(Object otherObject)  {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (getClass() != otherObject.getClass()) return false;

        User other = (User) otherObject;
        return login.equals(other.getLogin())
                && password.equals(other.getPassword());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 53 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }
}
