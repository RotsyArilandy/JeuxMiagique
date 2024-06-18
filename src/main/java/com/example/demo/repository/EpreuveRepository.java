package com.example.demo.repository;

import com.example.demo.entity.Delegation;
import com.example.demo.entity.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpreuveRepository extends JpaRepository<Epreuve, Long> {
}
