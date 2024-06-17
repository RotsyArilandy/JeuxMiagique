package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "delegation_entite")
@NoArgsConstructor
@AllArgsConstructor
public class Delegation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column (name = "nom")
    private String nom;
    @Column (name = "médailleOr")
    private int nbOr;
    @Column (name = "medailleArgent")
    private int nbArgent;
    @Column (name = "medailleBronze")
    private int nbBronze;


}
