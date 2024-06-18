package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "epreuve")
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idE;
    @Column(name = "nom")
    private String nomE;
    @Column(name ="")
    private LocalDate dateE;
    @JoinColumn
    private Infrastructure infrastructure;
    @Column (name = "nbPlaceEnVente")
    private int nbPlace;


}

