package com.example.demo.controller;

import com.example.demo.entity.Delegation;
import com.example.demo.entity.Participant;
import com.example.demo.exceptions.CompteDejaExistantException;
import com.example.demo.exceptions.CompteIntrouvableException;
import com.example.demo.exceptions.DelegationDejaExistant;
import com.example.demo.exceptions.DelegationIntrouvable;
import com.example.demo.service.OrganisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organisateur")
public class OrganisateurController {


    @Autowired
    OrganisateurService organisateurService;

    //Créer une délégation
    @PostMapping ("/createDelegation")
    public Delegation newDelegation (String nomDelegation) throws DelegationDejaExistant {
        return organisateurService.saveDelegation(nomDelegation);
    }

    //Afficher les délégation par Id
    @GetMapping("/{id}")
    public <Optional> java.util.Optional<Delegation> getDelegation(Long id) throws DelegationIntrouvable {
        return organisateurService.findDelegation(id);
    }

    //Afficher les toutes les délégations
    @GetMapping("/allDelegation")
    public List <Delegation> getAllDelegation (){
        return organisateurService.findAllDelegation();
    }

    //Supprimer une délegation
    @DeleteMapping("/deleteDelegation")
    public String deleteDelegation (String d) throws DelegationIntrouvable {
        return organisateurService.deleteDelegation(d);
    }

    //Supprimer Toutes les Delegations
    @DeleteMapping("/deleteAllDelegation")
    public String deleteAllDelegation(){
        return organisateurService.deleteAllDelegation();
    }

    //Creer un participant
    @PostMapping("/createParticipant")
    public Participant addParticipant (String nom , String prenom ,  String mail, Delegation del) throws CompteDejaExistantException {
        return organisateurService.saveParticipant(nom ,prenom ,mail,del);
    }

    //Supprimer un participant
    @DeleteMapping("/deleteParticipant")
    public String deleteParticipant (@RequestBody Participant participant) throws CompteIntrouvableException {
        return organisateurService.deleteParticipant(participant);
    }

    //Récupérer liste de tous les participants
    @GetMapping("/allParticipant")
    public List <Participant> getAllParticipant(){
        return organisateurService.findAllParticipant();
    }

    //Supprimer tous les participants
    @DeleteMapping("/deleteAllParticipant")
    public String deleteAllParticipant(){
        organisateurService.deleteAllParticipant();
        return "Toutes les participants ont bien été supprimées";
    }

    /*
    //Ajouter un organisateur ?????
    @PostMapping("/create")
    public Organisateur addOrganisation (@RequestBody Organisateur organisateur){
        return organisateurService.saveOrganisteur(organisateur);
    }

    //Supprimer une organisateur
    @DeleteMapping("/delete/{id}")
    public String deleteOrganisation(@PathVariable Long id){
        organisateurService.deleteOrganisationById(id);
        return "L'organisateur "+ id +" a bien été supprimé";
    }


    //Récupérer la liste de toutes les organisateurs
    @GetMapping("/listeOrganisateurs")
    public List<Organisateur> getAllOrganisateur(){

        return organisateurService.findAllOrganisateur();
    }

     */

}
