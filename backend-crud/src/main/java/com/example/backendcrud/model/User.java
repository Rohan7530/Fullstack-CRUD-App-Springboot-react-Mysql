package com.example.backendcrud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity

public class User {
    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid")
    private String id;
    private String username;
    private String name;
    private String email;

    public String getId() {
        return id;
    }

    public void generateIdAndSet() {
        this.id = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
