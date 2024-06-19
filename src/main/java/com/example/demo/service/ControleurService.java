package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exceptions.BilletDejaUtiliseException;
import com.example.demo.exceptions.BilletIntrouvableException;
import com.example.demo.exceptions.MauvaisDroitsException;
import com.example.demo.repository.BilletRepository;
import com.example.demo.repository.ControleurRepository;
import com.example.demo.repository.SpectateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ControleurService {

    @Autowired
    private ControleurRepository controleurRepository;

    @Autowired
    private BilletRepository billetRepository;


    //Vérifier validité des billets
    public String validerBillet(String mail , Long idB) throws BilletIntrouvableException, MauvaisDroitsException, BilletDejaUtiliseException {
        Controleur c = controleurRepository.findByMail(mail);
        Billet b = billetRepository.getById(idB);
        if (c == null || c.getRole() != Role.CONTROLLEUR){
            throw new MauvaisDroitsException("Il faut être un controlleur pour valiser un billet");
        }
        if (b == null) {
            throw new BilletIntrouvableException("Le billet est introuvable");
        }
        else if (b.getEtatBillet() == Etat.VALIDE ){
            throw new BilletDejaUtiliseException("Le billet a déjà été utilisé");
        }
        else {
            b.setEtatBillet(Etat.VALIDE);
            return "Votre billet a été validé";
        }
    }
}
