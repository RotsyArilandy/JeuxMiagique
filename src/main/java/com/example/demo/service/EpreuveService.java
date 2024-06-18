package com.example.demo.service;

import com.example.demo.entity.Epreuve;
import com.example.demo.repository.EpreuveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpreuveService {

    @Autowired
    private EpreuveRepository epreuveRepository;


    //Récupérer liste de toutes les épreuves
    public List<Epreuve> findAllEpreuve() {
        return epreuveRepository.findAll();
    }
}
