package com.example.demo.service;

import com.example.demo.entity.Spectateur;
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

    public Spectateur saveSpectateur(Spectateur spectateur) {

        return spectateurRepository.save(spectateur);
    }

    public Optional<Spectateur> findSpectateurById(Long id) {

        return spectateurRepository.findById(id);
    }

    public void deleteSpectateurById(Long id) {

        spectateurRepository.deleteById(id);
    }

    public List<Spectateur> findAllSpectateurs() {

        return spectateurRepository.findAll();
    }

    public void deleteAll() {
        spectateurRepository.deleteAll();
    }
}

