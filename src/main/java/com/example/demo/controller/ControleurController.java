package com.example.demo.controller;

import com.example.demo.entity.Controleur;
import com.example.demo.repository.ControleurRepository;
import com.example.demo.service.ControleurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/controleur")
public class ControleurController {

    @Autowired
    ControleurService controleurService;

    //Scanner les billets

    //Vérifier validier billet
}
