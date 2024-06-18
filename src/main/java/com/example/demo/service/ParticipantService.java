package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exceptions.CompteDejaExistantException;
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
    public Participant saveParticipant(String nom , String prenom ,  String mail, Delegation del) throws CompteDejaExistantException {
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
        return participantRepository.save(new Participant(nom, prenom, mail, del));
    }

    //Supprimer un participant avec vérification


    //Sauvegarder un participant avec vérification
    public Participant saveParticipant(Participant participant) {

        return participantRepository.save(participant);
    }

    // Rechercher un participant
    public Optional<Participant> findParticipantById(Long id) {

        return participantRepository.findById(id);
    }

    //Supprimer un participant
    public void deleteParticipantById(Long id) {

        participantRepository.deleteById(id);
    }

    //Afficher la liste des participants
    public List<Participant> findAllParticipant() {

        return participantRepository.findAll();
    }

    public void deleteAll() {

        participantRepository.deleteAll();
    }
}
