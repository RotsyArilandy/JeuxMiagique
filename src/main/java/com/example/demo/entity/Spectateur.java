package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@Entity
@Data
@Table (name = "spectateur_entite")
@NoArgsConstructor
@AllArgsConstructor
public class Spectateur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column (name = "nom")
    private String nom;
    @Column (name = "prenom")
    private String prenom;
    @Column (name = "mail")
    private String mail;
    @Column (name = "role")
    private Role role = Role.SPECTATEUR;
}
