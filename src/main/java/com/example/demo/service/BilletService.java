package com.example.demo.service;

import com.example.demo.entity.Billet;
import com.example.demo.entity.Epreuve;
import com.example.demo.entity.Etat;
import com.example.demo.entity.Spectateur;
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



    //Enregistrer un billet + Sans vérification
    public String saveReservartion(String mail, String nomEpreuve) {
        Spectateur s = spectateurRepository.findByMail(mail);
        Epreuve e = epreuveRepository.findByName(nomEpreuve);
        Billet b= new Billet (e.getIdE(),s.getId(),Etat.VENDU);
        billetRepository.save(b);
        return "Le billet a été validé "+ b;
    }

    //Annuler une reservation + sans vérification
    public String cancelReservation(Long idBillet, Long idSpectateur) throws ReservationIntrouvableException {
        Optional <Billet> b = billetRepository.findById(idBillet);
        if ( b == null ){
            throw new ReservationIntrouvableException("La réservation est introuvable");
       }else{
            billetRepository.delete(b);
            return "Le billet "+idBillet+ " a été annulé";
        }
    }


}
