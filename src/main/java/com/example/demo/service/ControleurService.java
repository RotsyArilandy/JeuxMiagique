package com.example.demo.service;

import com.example.demo.entity.Controleur;
import com.example.demo.entity.Organisateur;
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

    public void deleteAll(){
        controleurRepository.deleteAll();
    }

    public Optional<Controleur> findControleurById(Long id) {
        return controleurRepository.findById(id);
    }

    public Controleur saveControleur(Controleur controleur) {
        return controleurRepository.save(controleur);
    }

    public void deleteControleurById(Long id) {
        controleurRepository.deleteById(id);
    }

    public List<Controleur> findAllControleur() {
        return controleurRepository.findAll();
    }
}
