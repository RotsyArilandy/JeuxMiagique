package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exceptions.CompteDejaExistantException;
import com.example.demo.exceptions.MauvaisDroitsException;
import com.example.demo.exceptions.ParticipantIntrouvableException;
import com.example.demo.repository.ControleurRepository;
import com.example.demo.repository.OrganisateurRepository;
import com.example.demo.repository.ParticipantRepository;
import com.example.demo.repository.SpectateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.Param;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    @Autowired
    private SpectateurRepository spectateurRepository;
    @Autowired
    private OrganisateurRepository organisateurRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private ControleurRepository controleurRepository;

    //Création de participant avec vérification
    public Participant saveParticipant(String nom, String prenom, String mail, Delegation del) throws CompteDejaExistantException {
        Participant p = participantRepository.findByMail(mail);
        Organisateur o = organisateurRepository.findByMail(mail);
        Controleur c = controleurRepository.findByMail(mail);
        Spectateur s = spectateurRepository.findByMail(mail);
        if (p == null) {
            if (s != null) {
                throw new CompteDejaExistantException("Ce mail est déjà utilisé pour un compte Spectateur : " + mail);
            } else if (p != null) {
                throw new CompteDejaExistantException("Ce mail est déjà utilisé pour un compte Participant : " + mail);
            } else if (c != null) {
                throw new CompteDejaExistantException("Ce mail est déjà utilisé pour un compte Controleur : " + mail);
            }
        }
        return participantRepository.save(new Participant(nom, prenom, mail));
    }

    //Supprimer un participant avec vérification par Organisateur
    public String supprimerParticipant(String mail, Participant participant) throws ParticipantIntrouvableException, MauvaisDroitsException {
        Organisateur o = organisateurRepository.findByMail(mail);
        Participant p = participantRepository.findByMail(participant.getMail());
        if (p == null){
            throw new ParticipantIntrouvableException("La participant que vous voulez supprimer n'existe pas");
        }
        else if( o == null || o.getRole() != Role.ORGANISATEUR){
            throw new MauvaisDroitsException("Seul un organisateur peru supprimer un participant");
        }
        else{
            participantRepository.deleteById(p.getId());
        }
        return "La participant a bien été supprimé";
    }


    // Rechercher un participant
    public Participant findParticipant(String mail) {
        return participantRepository.findByMail(mail);
    }


    //Afficher la liste des participants
    public List<Participant> findAllParticipant() {

        return participantRepository.findAll();
    }

}


