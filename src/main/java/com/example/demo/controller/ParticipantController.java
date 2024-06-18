package com.example.demo.controller;

import com.example.demo.entity.Epreuve;
import com.example.demo.entity.Participant;
import com.example.demo.entity.Spectateur;
import com.example.demo.service.EpreuveService;
import com.example.demo.service.ParticipantService;
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




    //Récupérer la liste de épreuves dispo
    @GetMapping("/listeEpreuve")
    public List<Epreuve> getAllEpreuve(){
        return epreuveService.findAllEpreuve();
    }

    //S'inscrire au épreuve


    //Consulter les résultats + Classement délégation

}
