package com.example.demo.controller;

import com.example.demo.entity.Spectateur;
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

    //Récupérer un spectateur par id
    @GetMapping ("/{id}")
    public Optional<Spectateur> getSpectateurById (@PathVariable Long id){
        return spectateurService.findSpectateurById(id);
    }

    //Ajouter un spectateur
    @PostMapping("/create")
    public Spectateur addSpectateur (@RequestBody Spectateur spectateur){
        return spectateurService.saveSpectateur(spectateur);
    }

    //Supprimer un spectateur
    @DeleteMapping("/delete/{id}")
    public String deleteSpectateur(@PathVariable Long id){
        spectateurService.deleteSpectateurById(id);
        return "Le spectateur "+ id +" a bien été supprimé";
    }

    @DeleteMapping(("/deleteAll"))
    public String deleteAllSpectateur(){
        spectateurService.deleteAll();
        return "Toutes les participants ont bien été supprimées";
    }


    //Récupérer la liste de tous les spectateurs
    @GetMapping("/listeSpectateur")
    public List<Spectateur> getAllSpectateur(){
        return spectateurService.findAllSpectateurs();
    }

}
