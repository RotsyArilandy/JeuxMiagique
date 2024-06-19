package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exceptions.CompteDejaExistantException;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SpectateurService {
    @Autowired
    private SpectateurRepository spectateurRepository;
    @Autowired
    private OrganisateurRepository organisateurRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private ControleurRepository controleurRepository;
    @Autowired
    private EpreuveRepository epreuveRepository;

    public Spectateur saveSpectateur(Spectateur spectateur) throws CompteDejaExistantException {
        Participant p = participantRepository.findByMail(spectateur.getMail());
        Organisateur o = organisateurRepository.findByMail(spectateur.getMail());
        Controleur c = controleurRepository.findByMail(spectateur.getMail());
        Spectateur s = spectateurRepository.findByMail(spectateur.getMail());
        if (s == null) {
            if (o != null) {
                throw new CompteDejaExistantException("Ce mail est déjà utilisé pour un compte Organisateur : " + spectateur.getMail());
            } else if (p != null) {
                throw new CompteDejaExistantException("Ce mail est déjà utilisé pour un compte Participant : " + spectateur.getMail());
            } else if (c != null) {
                throw new CompteDejaExistantException("Ce mail est déjà utilisé pour un compte Controleur : " + spectateur.getMail());
            }
        }
        return spectateurRepository.save(spectateur);
    }

    //Supprimer son compte Spectateur
    public void deleteSpectateurById (Long id){
        spectateurRepository.deleteById(id);
    }

    //Afficher son compte Spectateur par ID
    public Optional<Spectateur> findSpectateurById(Long id){
        return spectateurRepository.findById(id);
    }

    //Récupérer liste de toutes les épreuves
    public List<Epreuve> findAllEpreuve() {
        return epreuveRepository.findAll();
    }
}


