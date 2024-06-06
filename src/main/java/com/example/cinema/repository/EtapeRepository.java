package com.example.cinema.repository;

import com.example.cinema.model.Etape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtapeRepository extends JpaRepository<Etape, Long> {

}