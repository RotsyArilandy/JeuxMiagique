package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resultat_entite")
public class Resultat {
    @Column(name = "idEpreuve")
    private Long idE;
    @Column(name = "idParticipant")
    private Long idP;
    @Column(name = "tempsPoints")
    private Long tempsPoints;
    @Column(name = "position")
    private int pos;
}
