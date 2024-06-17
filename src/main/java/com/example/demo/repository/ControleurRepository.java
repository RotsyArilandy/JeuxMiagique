package com.example.demo.repository;

import com.example.demo.entity.Controleur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControleurRepository extends JpaRepository<Controleur, Long> {
    Controleur findByMail(String mail);
}
