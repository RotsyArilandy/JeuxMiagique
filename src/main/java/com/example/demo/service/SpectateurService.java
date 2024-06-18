package com.example.demo.service;

import com.example.demo.entity.Controleur;
import com.example.demo.entity.Organisateur;
import com.example.demo.entity.Participant;
import com.example.demo.entity.Spectateur;
import com.example.demo.exceptions.CompteDejaExistantException;
import com.example.demo.repository.ControleurRepository;
import com.example.demo.repository.OrganisateurRepository;
import com.example.demo.repository.ParticipantRepository;
import com.example.demo.repository.SpectateurRepository;
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


        public Optional<Spectateur> findSpectateurById(Long id){


            return spectateurRepository.findById(id);
        }

        public void deleteSpectateurById (Long id){


            spectateurRepository.deleteById(id);
        }

        public List<Spectateur> findAllSpectateurs () {

            return spectateurRepository.findAll();
        }

        public void deleteAll () {
            spectateurRepository.deleteAll();
        }
    }


