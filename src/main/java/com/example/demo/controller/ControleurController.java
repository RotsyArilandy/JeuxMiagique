package com.example.demo.controller;

import com.example.demo.entity.Controleur;
import com.example.demo.repository.ControleurRepository;
import com.example.demo.service.ControleurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/controleur")
public class ControleurController {

    @Autowired
    ControleurService controleurService;

    @GetMapping("/{id}")
    public Optional<Controleur> getControleurById (@PathVariable Long id){
        return controleurService.findControleurById(id);
    }

    @PostMapping("/create")
    public Controleur addControlleur (@RequestBody Controleur controleur){
        return controleurService.saveControleur(controleur);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteControleur(@PathVariable Long id){
        controleurService.deleteControleurById(id);
        return "Le controlleur "+id+" a bien été supprimé";
    }

    @DeleteMapping(("/deleteAll"))
    public String deleteControleur(){
        controleurService.deleteAll();
        return "Toutes les controleurs ont bien été supprimées";
    }

    @GetMapping("/listeControleur")
    public List<Controleur> getAllControleur(){
        return controleurService.findAllControleur();
    }

}
