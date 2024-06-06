package com.example.cinema.repository;

import com.example.cinema.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    @Query("select c from Categorie c where c.nom = ?1")
    Categorie getCategorieByNom(String nom);
}