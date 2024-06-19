package com.example.demo.controller;

import com.example.demo.entity.Controleur;
import com.example.demo.entity.Delegation;
import com.example.demo.entity.Organisateur;
import com.example.demo.entity.Participant;
import com.example.demo.exceptions.*;
import com.example.demo.service.EpreuveService;
import com.example.demo.service.OrganisateurService;
import com.example.demo.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organisateur")
public class OrganisateurController {


    @Autowired
    OrganisateurService organisateurService;

    @Autowired
    EpreuveService epreuveService;

    @Autowired
    ParticipantService participantService;

    //Créer une délégation
    @PostMapping ("/createDelegation/{mail}")
    public Delegation newDelegation (String nomDelegation, String mail) throws DelegationDejaExistantException, MauvaisDroitsException {
        return organisateurService.saveDelegation(nomDelegation, mail);
    }

    //Afficher les délégation par Id
    @GetMapping("/{id}")
    public <Optional> java.util.Optional<Delegation> getDelegation(Long id) throws DelegationIntrouvableException {
        return organisateurService.findDelegation(id);
    }

    //Afficher les toutes les délégations
    @GetMapping("/allDelegation")
    public List <Delegation> getAllDelegation (){
        return organisateurService.findAllDelegation();
    }

    //Supprimer une délegation
    @DeleteMapping("/deleteDelegation/{mail}")
    public String deleteDelegation (@PathVariable String mail,@PathVariable String d) throws DelegationIntrouvableException, MauvaisDroitsException {
        return organisateurService.deleteDelegation(mail,d);
    }

    //Supprimer Toutes les Delegations
    @DeleteMapping("/deleteAllDelegation/{mail}")
    public String deleteAllDelegation(String mail) throws MauvaisDroitsException {
        return organisateurService.deleteAllDelegation(mail);
    }

    //Creer un participant
    @PostMapping("/createParticipant/{mail}")
    public String addParticipant (@PathVariable String n, @PathVariable String nom , @PathVariable String prenom ,  @PathVariable String mail, @PathVariable String del) throws CompteDejaExistantException, MauvaisDroitsException, CompteIntrouvableException {
        return organisateurService.saveParticipant(n,nom ,prenom ,mail,del);
    }

    //Supprimer un participant
    @DeleteMapping("/deleteParticipant/{mail}")
    public String deleteParticipant (@PathVariable String mail,@RequestBody Participant participant) throws CompteIntrouvableException, ParticipantIntrouvableException, MauvaisDroitsException {
        return participantService.supprimerParticipant(mail,participant);
    }

    //Récupérer liste de tous les participants
    @GetMapping("/allParticipant")
    public List <Participant> getAllParticipant(){
        return participantService.findAllParticipant();
    }

    //
    @GetMapping("/participant/{mail}")
    public Participant getParticipant(@PathVariable String mail){
        return participantService.findParticipant(mail);
    }

    //Supprimer tous les participants
    @DeleteMapping("/deleteAllParticipant/{mail}")
    public String deleteAllParticipant(){
        return organisateurService.deleteAllParticipant();

    }

    //Ajouter un controleur
    @PostMapping("/addControleur")
    public Controleur ajouterControler(@PathVariable String nom,@PathVariable String prenom, @PathVariable String mail) throws CompteDejaExistantException {
        return organisateurService.saveControleur(nom, prenom, mail);
    }



    //Ajouter un organisateur
    @PostMapping("/create/{mail}")
    public Organisateur addOrganisation (@RequestBody Organisateur organisateur) throws CompteDejaExistantException {
        return organisateurService.saveOrganisateur(organisateur);
    }

    //Supprimer une organisateur
    @DeleteMapping("/delete/{id}")
    public String deleteOrganisation(@PathVariable Long id) throws CompteIntrouvableException {
        organisateurService.deleteOrganisateurById(id);
        return "L'organisateur "+ id +" a bien été supprimé";
    }

    //Récupérer la liste de toutes les organisateurs
    @GetMapping("/listeOrganisateurs")
    public List<Organisateur> getAllOrganisateur(){

        return organisateurService.findAllOrganisateur();
    }

    //Ajouter épreuve
    @PostMapping("/createEpreuve/{mail}")
    public String ajouterEpreuve (@PathVariable String mail , @PathVariable String nomEpreuve, @PathVariable LocalDate dateE, @PathVariable String infrastructure, @PathVariable int nbPlaceDispo) throws EpreuveDejaExistantException, InfrastructureIntrouvableException, MauvaisDroitsException {
        return organisateurService.addEpreuve(mail, nomEpreuve, dateE, infrastructure,nbPlaceDispo);
    }


    //Supprimer une épreuve
    @DeleteMapping("/deleteEpreuve/{mail}")
    public String supprimerEpreuve (@PathVariable String mail , @PathVariable String nomEpreuve, @PathVariable LocalDate dateE, @PathVariable String infrastructure, @PathVariable int nbPlaceDispo) throws EpreuveIntrouvableException, InfrastructureIntrouvableException, MauvaisDroitsException {
        return organisateurService.deleteEpreuve(mail, nomEpreuve, dateE, infrastructure,nbPlaceDispo);
    }

    //Supprimer toutes les épreuves
    @DeleteMapping("/deleteAllEpreuve/{mail}")
    public String supprimerToutesEpreuves () {
        return organisateurService.deleteAllEpreuve();
    }

    //Modifier Nombre limite de participant
    @PutMapping("/updateEpreuveNbMax/{mail}/{idE}/{nbMax}")
    public String updateNbParticipant(@PathVariable String mail, @PathVariable Long idE , int nbMax) throws MauvaisDroitsException, EpreuveIntrouvableException {
        return organisateurService.updateNbParticipant(mail, idE, nbMax);
    }

    //Modifier nombre de billet mis en vente
    @PutMapping("/updateEpreuveNbBillet/{mail}/{idE}/{nbBillet}")
    public String updateNbBillet(@PathVariable String mail, @PathVariable Long idE ,@PathVariable int nbMax) throws MauvaisDroitsException, EpreuveIntrouvableException {
        return organisateurService.updateNbBillet(mail,idE, nbMax);
    }

    //Gérer les calendrier des épreuves
    @PutMapping("/gererDateEpreuve/{mail}")
    public String updateDateEpreuve(@PathVariable String mail,@PathVariable String nomEpreuve ,@PathVariable LocalDate date) throws MauvaisDroitsException, EpreuveIntrouvableException {
        return organisateurService.setDate(mail, nomEpreuve, date);}




        //Consulter les statistiques


    }

