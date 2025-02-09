package com.example.demo.repository;

import com.example.demo.entity.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DelegationRepository extends JpaRepository<Delegation, Long> {
    Delegation findByNom(String nom);
}

