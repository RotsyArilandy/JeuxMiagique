package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "participant_entite")
public class Participant{
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
    private Role role = Role.PARTICIPANT;

    @JoinColumn (name = "delegation")
    @ManyToOne
    private Delegation delegation;

    public Participant(String nom, String prenom, String mail, Delegation delegation) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.delegation = delegation;
        this.role = Role.PARTICIPANT;
    }
}
