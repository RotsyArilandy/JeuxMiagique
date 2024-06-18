package com.example.demo.controller;


import com.example.demo.exceptions.ParticipantIntrouvableException;
import com.example.demo.service.EpreuveService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/evenement")
public class EpreuveController {
    @Autowired
    private EpreuveService epreuveService;
    *
    //Consulter épreuves*


    //S'inscrire à une épreuve
    @PostMapping("/participer/{mail}/{nomEpreuve}")
    public  String participate(@PathVariable String mail, @PathVariable String nomEpreuve) throws ParticipantIntrouvableException {
        return epreuveService.participerEpreuve(mail,nomEpreuve);
    }

    //Se désinscrire à une épreuve
    @DeleteMapping("/désinscrire/{mail}/{nomEpreuve}")
    public  String désinscrire(@PathVariable String mail, @PathVariable String nomEpreuve){
        return epreuveService.désinscrireEpreuve(mail,nomEpreuve);
    }
}
