package com.example.tsinjo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Help {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Beneficiary beneficiary;

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;

    private String description;
}
