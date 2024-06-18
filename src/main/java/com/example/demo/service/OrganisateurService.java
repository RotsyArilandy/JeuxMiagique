package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exceptions.CompteDejaExistantException;
import com.example.demo.exceptions.CompteIntrouvableException;
import com.example.demo.exceptions.DelegationDejaExistant;
import com.example.demo.exceptions.DelegationIntrouvable;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganisateurService {
    @Autowired
    private SpectateurRepository spectateurRepository;
    @Autowired
    private OrganisateurRepository organisateurRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private ControleurRepository controleurRepository;
    @Autowired
    private DelegationRepository delegationRepository;

    // Afficher une délégation
    public Optional<Delegation> findDelegation (Long id) throws DelegationIntrouvable {
        Optional <Delegation> d = delegationRepository.findById(id);
        if (d == null){
            throw new DelegationIntrouvable("La délégation que vous recherchez n'existe pas");
        }
        return d;
    }

    //Afficher toutes les délégations
    public List<Delegation> findAllDelegation() {
        return delegationRepository.findAll();
    }

    //Supprimer toutes les délégations
    public String deleteAllDelegation() {
        delegationRepository.deleteAll();
        return "Toutes les délégations sont bien supprimées";
    }

    //Supprimer une délegation
    public String deleteDelegation(String nomDelegation) throws DelegationIntrouvable {
        Delegation d = delegationRepository.findByNom(nomDelegation);
        if (d == null){
            throw new DelegationIntrouvable("La délégation que vous essayez de supprimer n'existe pas");
        }
        delegationRepository.delete(d);
        return "La délégation a bien été supprimée: "+nomDelegation;
    }


    //Creer une delegation
    public Delegation saveDelegation(String nomDelegation) throws DelegationDejaExistant {
        Delegation d = delegationRepository.findByNom( nomDelegation);
        if (d != null) {
            throw new DelegationDejaExistant("Le nom de la délégation que essayez de créer exst déjà pris: " + d.getNom());
        }
        return delegationRepository.save(new Delegation(nomDelegation,0,0,0 , new ArrayList<Participant>()));

    }


    //Créer un participant
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

    //Afficher un participant
    public Optional<Participant> findParticipant(Long id) throws CompteIntrouvableException {
        Optional <Participant> p = participantRepository.findById(id);
        if (p == null)
        {
            throw new CompteIntrouvableException("Le participant n'existe pas");
        }
        return p;
    }

    //Afficher tous les participants
    public List<Participant> findAllParticipant() {

        return participantRepository.findAll();
    }

    //Supprimer une participant par Id
    public String deleteParticipant(Participant participant) throws CompteIntrouvableException {
        Participant p = participantRepository.findByMail(participant.getMail());
        if (p == null)
        {
            throw new CompteIntrouvableException("Le participant que vous essayez de supprimer n'existe pas");
        }
        participantRepository.deleteById(p.getId());
        return "Le participant "+ p.getId() + " a été supprimé";
    }

    //Supprimer tous les participants
    public String deleteAllParticipant() {
        participantRepository.deleteAll();
        return "Tous les participants sont supprimés";
    }

    //Creer un controleur
    public Controleur saveControleur(String nom , String prenom ,  String mail) throws CompteDejaExistantException {
        Participant p = participantRepository.findByMail(mail);
        Organisateur o = organisateurRepository.findByMail(mail);
        Controleur c = controleurRepository.findByMail(mail);
        Spectateur s = spectateurRepository.findByMail(mail);
        if (c == null) {
            if (s != null) {
                throw new CompteDejaExistantException("Ce mail est déjà utilisé pour un compte Spectateur : " + mail);
            } else if (p != null) {
                throw new CompteDejaExistantException("Ce mail est déjà utilisé pour un compte Participant : " + mail);
            } else if (o != null) {
                throw new CompteDejaExistantException("Ce mail est déjà utilisé pour un compte Organisateur : " + mail);
            }
        }
        Controleur cont = new Controleur(nom, prenom, mail);
        return controleurRepository.save(cont);
    }


    //Créer un organisateur + vérification
    public Organisateur saveOrganisteur(Organisateur organisateur) throws CompteDejaExistantException {
        Organisateur o = organisateurRepository.findByMail(organisateur.getMail());
        if (o != null){
            throw new CompteDejaExistantException("Cet organisateur existe déjà");
        }
        return organisateurRepository.save(organisateur);
    }

    //Supprimer un organisateur par ID
    public String deleteOrganisationById(Long id) throws CompteIntrouvableException {
        Optional<Organisateur> o = organisateurRepository.findById(id);
        if  ( o == null){
            throw new CompteIntrouvableException("Cet organisateur n'existe pas");
        }
        return "L'organisateur "+id + " a bien été supprimé";

    }

    //Récupérer la liste de tous les organisateurs
    public List<Organisateur> findAllOrganisateur() {
        return organisateurRepository.findAll();
    }
}
