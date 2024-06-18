package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

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
    private int nbPlaceDispo;
    @Column (name = "nbPlaceVendu")
    private int nbPlaceVendu;
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



}

