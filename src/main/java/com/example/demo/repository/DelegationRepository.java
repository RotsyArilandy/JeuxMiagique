package com.example.demo.repository;

import com.example.demo.entity.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelegationRepository extends JpaRepository<Delegation, Long> {
}

