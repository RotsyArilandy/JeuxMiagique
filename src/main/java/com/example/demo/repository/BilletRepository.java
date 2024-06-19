package com.example.demo.repository;

import com.example.demo.entity.Billet;
import com.example.demo.entity.Controleur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BilletRepository extends JpaRepository<Billet, Long> {
    void delete(Optional<Billet> b);
}
