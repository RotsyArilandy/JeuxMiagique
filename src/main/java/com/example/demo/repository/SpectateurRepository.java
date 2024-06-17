package com.example.demo.repository;

import com.example.demo.entity.Spectateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpectateurRepository extends JpaRepository<Spectateur, Long> {
    Spectateur findByMail(String mail);
}
