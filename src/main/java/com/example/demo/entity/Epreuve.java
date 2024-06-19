package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionIdMutability;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private String infrastructure;
    @Column (name = "nbPlaceEnVente")
    private int nbPlaceDispo;
    @Column (name = "nbPlaceVendu")
    private int nbPlaceVendu;
    @Column (name = "nbParticipantMax")
    private int nbParticipantMax;
    @Column (name = "listeJoueurInscrit")
    private ArrayList<Participant> listeParticipant;

    public void inscrireParticcipant(Participant participant){
        listeParticipant.add(participant);
        nbPlaceDispo--;
        nbPlaceVendu++;
    }

    public Boolean rechercherParticipant (Participant p){
        for (Participant param : listeParticipant) {
            if (param.equals(p)){
                // Participant trouvé
                return true;
            }
        }
        return false;
    }

    public Epreuve(String nomE, LocalDate dateE, String infrastructure, int nbPlaceDispo, int nbParticipantMax ) {
        this.nomE = nomE;
        this.dateE = dateE;
        this.infrastructure = infrastructure;
        this.nbParticipantMax = nbParticipantMax;
        this.nbPlaceDispo = nbPlaceDispo;
        this.nbPlaceVendu = 0;
        this.listeParticipant = new ArrayList<>(nbParticipantMax);
    }

    public Epreuve(String nomE){
        this.nomE = nomE;

    }

    public void setNbMax(int nbMax) {
        this.nbParticipantMax= nbMax;
    }

    public void setNbMaxBillet(int nbMax) {
        this.nbPlaceDispo= nbMax;
    }


}

