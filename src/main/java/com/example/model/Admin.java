package com.example.model;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer adminId;

    @Column(name = "name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at",insertable = false,updatable = false)
    private LocalDateTime createdAt;

    public Admin(){

    }

    public Admin(String username,String password){

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
