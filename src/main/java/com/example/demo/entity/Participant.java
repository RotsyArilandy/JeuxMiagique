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

    @Column (name = "delegation")
    @ManyToOne
    private Delegation delegation;
}
