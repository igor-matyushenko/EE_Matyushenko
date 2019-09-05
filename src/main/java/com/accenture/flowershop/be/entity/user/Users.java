package com.accenture.flowershop.be.entity.user;

import com.accenture.flowershop.fe.enums.customer.Role;
import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class Users {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORDS")
    private String passwords;

    @Column(name = "ROLE")
    private Role role;

    @Column(name = "ID_BUYER")
    private long id_buyer;

    public Users(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getId_buyer() {
        return id_buyer;
    }

    public void setId_buyer(long id_buyer) {
        this.id_buyer = id_buyer;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", passwords='" + passwords + '\'' +
                ", role=" + role +
                ", id_buyer=" + id_buyer +
                '}';
    }
}
