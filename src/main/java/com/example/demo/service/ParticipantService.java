package com.example.demo.service;

import com.example.demo.entity.Participant;
import com.example.demo.entity.Spectateur;
import com.example.demo.repository.ParticipantRepository;
import com.example.demo.repository.SpectateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    //Sauvegarder un participant
    public Participant saveParticipant(Participant participant) {

        return participantRepository.save(participant);
    }

    // Rechercher un participant
    public Optional<Participant> findParticipantById(Long id) {

        return participantRepository.findById(id);
    }

    //Supprimer un participant
    public void deleteParticipantById(Long id) {

        participantRepository.deleteById(id);
    }

    //Afficher la liste des participants
    public List<Participant> findAllParticipant() {

        return participantRepository.findAll();
    }

    public void deleteAll() {
        participantRepository.deleteAll();
    }
}
