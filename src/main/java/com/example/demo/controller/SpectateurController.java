package com.example.demo.controller;

import com.example.demo.entity.Epreuve;
import com.example.demo.entity.Spectateur;
import com.example.demo.exceptions.CompteDejaExistantException;
import com.example.demo.repository.SpectateurRepository;
import com.example.demo.service.SpectateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/spectateur")
public class SpectateurController {
    @Autowired
    private SpectateurService spectateurService;

    //S'inscrire en tant que spectateur
    @PostMapping("/create")
    public Spectateur addSpectateur (@RequestBody Spectateur spectateur) throws CompteDejaExistantException {
        return spectateurService.saveSpectateur(spectateur);
    }

    //Récupérer un spectateur par id
    @GetMapping ("/{id}")
    public Optional<Spectateur> getSpectateurById (@PathVariable Long id){
        return spectateurService.findSpectateurById(id);
    }


    //Se désinscrire en tant que spectateur
    @DeleteMapping("/delete/{id}")
    public String deleteSpectateur(@PathVariable Long id){
        spectateurService.deleteSpectateurById(id);
        return "Le spectateur "+ id +" a bien été supprimé";
    }

    //Consulter le programme des épreuves
    @GetMapping("/allEpreuves")
    public List<Epreuve> getAllEpreuve(){
        return spectateurService.findAll();
    }

    //Réserver les billets

    //Payer en ligne

    //Recevoir les billets electroniquement

    //Annuler reservation

}
