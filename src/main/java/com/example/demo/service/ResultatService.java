package com.example.demo.service;

import com.example.demo.entity.Resultat;
import com.example.demo.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultatService {
    @Autowired
    private ResultatRepository resultatRepository;

    //Afficher les résultats
    public List<Resultat> getAllResultats() {
        return resultatRepository.findAll();
    }
}
