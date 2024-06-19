package com.example.demo.service;

import com.example.demo.entity.Epreuve;
import com.example.demo.entity.Participant;
import com.example.demo.exceptions.ParticipantIntrouvableException;
import com.example.demo.repository.EpreuveRepository;
import com.example.demo.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EpreuveService {

    @Autowired
    private EpreuveRepository epreuveRepository;

    @Autowired
    private ParticipantRepository participantRepository;



    //Récupérer liste de toutes les épreuves
    public List<Epreuve> findAllEpreuve() {
        return epreuveRepository.findAll();
    }


    //Inscrire un participant à une épreuve
    public String participerEpreuve(String mail, String nomEpreuve) throws ParticipantIntrouvableException {
       Participant p =  participantRepository.findByMail(mail);
       Epreuve e = epreuveRepository.findByName(nomEpreuve);
       if (p == null ){
           throw new ParticipantIntrouvableException("Le partipant n'existe pas");
       }
       if (e == null) {
           throw new ParticipantIntrouvableException("L'épreuve n'existe pas");
        }
       /*Vérifier date incription
       else{
           if (LocalDate.now() - 7)>
       }*/
       e.inscrireParticcipant(p);
       return " Le joueur "+ mail+ " est inscrit à l'épreuve " + nomEpreuve;
    }

    //Désinscrire un partticipant
    public String désinscrireEpreuve(String mail, String nomEpreuve) {
        Participant p =  participantRepository.findByMail(mail);
        Epreuve e = epreuveRepository.findByName(nomEpreuve);
        if (e.rechercherParticipant(p) == false){
            return "Echec désangagement : Le participant n'est pas inscrit à l'épreuve";
        }
        else{
            //Ajouter vérification date de désangagement
            e.getListeParticipant().remove(p);
            return "Le participant est désisncrit";
        }

    }

    //Ajouter
    public String addEpreuve(String mail, String nomEpreuve) {
        return "";
    }

}
