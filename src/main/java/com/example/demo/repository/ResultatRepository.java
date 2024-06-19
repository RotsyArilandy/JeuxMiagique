package com.example.demo.repository;

import com.example.demo.entity.Delegation;
import com.example.demo.entity.Participant;
import com.example.demo.entity.Resultat;
import jakarta.servlet.http.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultatRepository extends JpaRepository<Resultat, Long> {
    List<Delegation> sortByMedaille();
}
