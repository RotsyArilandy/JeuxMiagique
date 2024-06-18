package com.example.demo.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
@Nullable
@Column (name = "médailleOr")
private int nbOr = 0;
@Nullable
@Column (name = "medailleArgent")
private int nbArgent = 0;
@Nullable
@Column (name = "medailleBronze")
private int nbBronze = 0;
@Nullable
@OneToMany(mappedBy = "delegation")
private ArrayList<Participant> participants ;

public Delegation(String nomDelegation, int i, int i1, int i2, ArrayList<Participant> participants) {
   this.nom = nomDelegation;
   this.nbOr = i;
   this.nbArgent = i2;
   this.nbBronze = i2;
   this.participants = participants;
}
}
