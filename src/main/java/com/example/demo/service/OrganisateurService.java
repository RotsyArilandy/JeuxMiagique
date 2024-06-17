package com.example.demo.service;

import com.example.demo.entity.Organisateur;
import com.example.demo.entity.Participant;
import com.example.demo.repository.OrganisateurRepository;
import com.example.demo.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganisateurService {

    @Autowired
    private OrganisateurRepository organisateurRepository;

    //Sauvegarder un organisateur
    public Organisateur saveOrganisteur(Organisateur organisateur) {

        return organisateurRepository.save(organisateur);
    }

    // Rechercher un organisateur
    public Optional<Organisateur> findOrganisateurById(Long id) {

        return organisateurRepository.findById(id);
    }

    //Supprimer un organisateur
    public void deleteOrganisationById(Long id) {

        organisateurRepository.deleteById(id);
    }

    //Afficher la liste des organisateurs
    public List<Organisateur> findAllOrganisateur() {

        return organisateurRepository.findAll();
    }

    public void deleteAll() {
        organisateurRepository.deleteAll();
    }
}
