package com.example.demo.controller;

import com.example.demo.entity.Organisateur;
import com.example.demo.exceptions.CompteDejaExistantException;
import com.example.demo.repository.OrganisateurRepository;
import com.example.demo.service.OrganisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organisateur")
public class OrganisateurController {

    @Autowired
    OrganisateurService organisateurService;


    //Récupérer  l'organisateur par id
    @GetMapping("/{id}")
    public Optional<Organisateur> getOrganisateurById (@PathVariable Long id){
        return organisateurService.findOrganisateurById(id);
    }

    //Ajouter un organisateur
    @PostMapping("/create")
    public Organisateur addOrganisation (@RequestBody Organisateur organisateur){
        return organisateurService.saveOrganisteur(organisateur);
    }


    //Supprimer une organization
    @DeleteMapping("/delete/{id}")
    public String deleteOrganisation(@PathVariable Long id){

        organisateurService.deleteOrganisationById(id);
        return "L'organisateur "+ id +" a bien été supprimé";
    }

    @DeleteMapping(("/deleteAll"))
    public String deleteOrganisation(){
        organisateurService.deleteAll();
        return "Toutes les organisations ont bien été supprimées";
    }


    //Récupérer la liste de toutes les organisations
    @GetMapping("/listeOrganisateurs")
    public List<Organisateur> getAllOrganisateur(){

        return organisateurService.findAllOrganisateur();
    }

}
