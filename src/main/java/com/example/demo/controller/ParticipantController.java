package com.example.demo.controller;

import com.example.demo.entity.Epreuve;
import com.example.demo.entity.Participant;
import com.example.demo.entity.Resultat;
import com.example.demo.entity.Spectateur;
import com.example.demo.exceptions.ParticipantIntrouvableException;
import com.example.demo.service.EpreuveService;
import com.example.demo.service.ParticipantService;
import com.example.demo.service.ResultatService;
import com.example.demo.service.SpectateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/participant")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private EpreuveService epreuveService;

    @Autowired
    private ResultatService resultatService;


    //Récupérer la liste de épreuves dispo
    @GetMapping("/listeEpreuve")
    public List<Epreuve> getAllEpreuve(){

        return epreuveService.findAllEpreuve();
    }

    //S'inscrire au épreuve
    @PostMapping("/inscireEpreuve/{mail}/{nomEpreuve}")
    public String inscrireEpreuve(@PathVariable String mail,@PathVariable String nomEpreuve) throws ParticipantIntrouvableException {
        return epreuveService.participerEpreuve(mail,nomEpreuve);
    }

    //Consulter les résultats
    @GetMapping("/getAllResultats")
    public List<Resultat> afficherResultat(){
        return resultatService.getAllResultats();
    }

    // Classement délégation

}
