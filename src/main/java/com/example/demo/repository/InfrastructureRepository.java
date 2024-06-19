package com.example.demo.repository;

import com.example.demo.entity.Infrastructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfrastructureRepository extends JpaRepository<Infrastructure, Long> {
    Infrastructure findByName(String infrastructure);
}
