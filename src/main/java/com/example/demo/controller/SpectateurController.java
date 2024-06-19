package com.example.demo.controller;

import com.example.demo.entity.Billet;
import com.example.demo.entity.Epreuve;
import com.example.demo.entity.Spectateur;
import com.example.demo.exceptions.CompteDejaExistantException;
import com.example.demo.exceptions.CompteSpectateurIntrouvableException;
import com.example.demo.exceptions.EpreuveIntrouvableException;
import com.example.demo.exceptions.ReservationIntrouvableException;
import com.example.demo.repository.BilletRepository;
import com.example.demo.repository.SpectateurRepository;
import com.example.demo.service.BilletService;
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
    @Autowired
    private BilletService billetService;


    //S'inscrire en tant que spectateur
    @PostMapping("/create/{mail}")
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
        return "Le spectateur "+ id +"a bien été supprimé";
    }

    //Consulter le programme des épreuves
    @GetMapping("/allEpreuves")
    public List<Epreuve> getAllEpreuve(){
        return spectateurService.findAllEpreuve();
    }

    //Réserver les billets
    @PostMapping("/reserverBillet/{mail}/{idE}")
    public String reserverBillet(@PathVariable String mail, @PathVariable String nomEpreuve) throws CompteSpectateurIntrouvableException, EpreuveIntrouvableException {
        return billetService.saveReservartion(mail,nomEpreuve);
    }


    //Annuler reservation
    @DeleteMapping("/annulerBillet/{mail}/{idE}")
    public String annulerBillet(@PathVariable Long idBillet, @PathVariable String mail) throws ReservationIntrouvableException, CompteSpectateurIntrouvableException {
        return billetService.cancelReservation(idBillet,mail);
    }

    //Payer en ligne
    @PutMapping("/payerBillet/{mail}/{idB}")
    public String payerBillet(@PathVariable String mail, @PathVariable Long idBillet) throws CompteSpectateurIntrouvableException, ReservationIntrouvableException {
        return billetService.payer(mail,idBillet);
    }

    //Recevoir les billets electroniquement



}
