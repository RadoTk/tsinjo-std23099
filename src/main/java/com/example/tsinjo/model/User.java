package com.example.tsinjo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class User {
    private String nom;
    private String prenom;
    private String email;
}
