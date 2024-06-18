package com.example.demo.entity;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;

@Entity
@Data
@Table(name = "infrastructure")
public class Infrastructure {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idI;
    @Column(name = "nomI")
    private String nomI;
    @Column (name = "adresseI")
    private String adresseI;
    @Column (name = "mailI")
    private String mailI;
    @Column (name = "capaciteMax" )
    private int capaciteMax;
}
