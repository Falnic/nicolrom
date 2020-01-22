package com.nicolrom.entities;

import com.nicolrom.enums.UserRightsEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idUser;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRightsEnum userRightsEnum;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int userId) {
        this.idUser = userId;
    }

    public UserRightsEnum getUserRightsEnum() {
        return userRightsEnum;
    }

    public void setUserRightsEnum(UserRightsEnum userRightsEnum) {
        this.userRightsEnum = userRightsEnum;
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
}
