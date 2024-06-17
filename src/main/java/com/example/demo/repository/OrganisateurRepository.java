package com.example.demo.repository;

import com.example.demo.entity.Organisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisateurRepository extends JpaRepository<Organisateur, Long> {
    Organisateur findByMail(String mail);
}
