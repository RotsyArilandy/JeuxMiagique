package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @JoinColumn (name = "idE")
    private Long idEpreuve;
    @JoinColumn (name = "idSpectateur")
    private Long idSpectateur;
    @Column (name = "prixBillet")
    private Long prixBillet;
    @Column (name = "etatBillet")
    private Etat etatBillet;

    public Billet(Long idEpreuve, Long idSpectateur, Etat etatBillet) {
        this.idEpreuve = idEpreuve;
        this.idSpectateur = idSpectateur;
        this.etatBillet = etatBillet;
    }
}
