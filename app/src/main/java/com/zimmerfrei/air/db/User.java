package com.zimmerfrei.air.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Timestamp;

/**
 * Created by Andro on 5.11.2014..
 */

@Table(name = "users")
public class User extends Model {
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "activated")
    private Integer activated;

    @Column(name = "activation_token")
    private String activation_token;

    @Column(name = "remember_token")
    private String remember_token;

    @Column(name = "role_id")
    private Role role_id;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "edited_at")
    private Timestamp edited_at;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public Integer getActivated() {
        return activated;
    }

    public String getActivation_token() {
        return activation_token;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public Role getRole_id() {
        return role_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getEdited_at() {
        return edited_at;
    }

    public User() {
        super();
    }

    public User(String name, String surname, String username, String password, String email,
                String phone, String avatar, Integer activated, String activation_token,
                String remember_token, Role role_id, Timestamp created_at, Timestamp edited_at) {
        super();
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.activated = activated;
        this.activation_token = activation_token;
        this.remember_token = remember_token;
        this.role_id = role_id;
        this.created_at = created_at;
        this.edited_at = edited_at;
    }
}
