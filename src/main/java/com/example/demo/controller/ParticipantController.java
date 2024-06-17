package com.example.demo.controller;

import com.example.demo.entity.Participant;
import com.example.demo.entity.Spectateur;
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

    //Récupérer un le participant par id
    @GetMapping("/{id}")
    public Optional<Participant> getParticipantById (@PathVariable Long id){
        return participantService.findParticipantById(id);
    }

    //Ajouter un paticipant
    @PostMapping("/create")
    public Participant addParticipant (@RequestBody Participant participant){
        return participantService.saveParticipant(participant);
    }

    //Supprimer un participant
    @DeleteMapping("/delete/{id}")
    public String deleteParticipant(@PathVariable Long id){

        participantService.deleteParticipantById(id);
        return "Le participant "+ id +" a bien été supprimé";
    }


    @DeleteMapping(("/deleteAll"))
    public String deleteAllParticipant(){
        participantService.deleteAll();
        return "Toutes les participants ont bien été supprimées";
    }


    //Récupérer la liste de tous les participants
    @GetMapping("/listePraticipant")
    public List<Participant> getAllParticipant(){
        return participantService.findAllParticipant();
    }

}
