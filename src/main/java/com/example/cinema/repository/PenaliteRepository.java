package com.example.cinema.repository;

import com.example.cinema.model.Penalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenaliteRepository extends JpaRepository<Penalite, Long> {
    @Query("select p from Penalite p where p.Etat = ?1")
    List<Penalite>getPenalitesByEtat(int etat);
}