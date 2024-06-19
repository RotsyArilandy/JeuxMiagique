package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exceptions.CompteSpectateurIntrouvableException;
import com.example.demo.exceptions.EpreuveIntrouvableException;
import com.example.demo.exceptions.ReservationIntrouvableException;
import com.example.demo.repository.BilletRepository;
import com.example.demo.repository.EpreuveRepository;
import com.example.demo.repository.SpectateurRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BilletService {

    @Autowired
    private BilletRepository billetRepository;

    @Autowired
    private SpectateurRepository spectateurRepository;

    @Autowired
    private EpreuveRepository epreuveRepository;


    //Enregistrer un billet +  vérification
    public String saveReservartion(String mail, String nomEpreuve) throws CompteSpectateurIntrouvableException,EpreuveIntrouvableException  {
        Spectateur s = spectateurRepository.findByMail(mail);
        Epreuve e = epreuveRepository.findByName(nomEpreuve);
        if (s == null || s.getRole() != Role.SPECTATEUR){
            throw new CompteSpectateurIntrouvableException("Il faut être Spectateur pour réserver un billet");
        }
        else if (e == null){
            throw new EpreuveIntrouvableException("L'épreuve à laquelle vous vouslez vous inscrire n'existe pas");
        }
        else {
            Billet b = new Billet(e.getIdE(), s.getId(), Etat.VENDU);
            billetRepository.save(b);
            return "Le billet a été validé " + b;
        }
    }

    //Annuler une reservation + sans vérification
    public String cancelReservation(Long idBillet, String mail) throws ReservationIntrouvableException, CompteSpectateurIntrouvableException {
        Spectateur s = spectateurRepository.findByMail(mail);
        Optional<Billet> b = billetRepository.findById(idBillet);
        if (b == null) {
            throw new ReservationIntrouvableException("La réservation est introuvable");
        } else if (s.getRole() != Role.SPECTATEUR) {
            throw new CompteSpectateurIntrouvableException("Il faut être Spectateur pour supprimer une réservation un billet");}
        else{
                billetRepository.delete(b);
                return "Le billet " + idBillet + " a été annulé";
            }
        }


    //Payer un billet
    public String payer(String mail, Long idBillet) throws ReservationIntrouvableException, CompteSpectateurIntrouvableException {
        Spectateur s = spectateurRepository.findByMail(mail);
        Optional<Billet> billet = billetRepository.findById(idBillet);
        if (billet == null) {
            throw new ReservationIntrouvableException("La réservation est introuvable");
        } else if (s.getRole() != Role.SPECTATEUR) {
            throw new CompteSpectateurIntrouvableException("Il faut se connecter pour payer en ligne un billet");}
        else{
            billet.ifPresent(b -> b.setEtatBillet(Etat.VENDU));
            return "Le billet " + idBillet + " a été payé";
            }
        }
    }

