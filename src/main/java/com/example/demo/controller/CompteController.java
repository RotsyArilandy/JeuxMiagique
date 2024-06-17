package com.example.demo.controller;

import com.example.demo.exceptions.CompteIntrouvableException;
import com.example.demo.service.CompteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompteController {
    @Autowired
    private CompteService connexionService;

    @GetMapping("/login/{mail}")
    public ResponseEntity<?> log (@PathVariable String mail){
        try {
            return ResponseEntity.ok(connexionService.login(mail));
        } catch (CompteIntrouvableException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    }
}

