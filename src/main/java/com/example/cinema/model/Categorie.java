package com.example.cinema.model;

import jakarta.persistence.*;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorie", nullable = false)
    private Long id_categorie;
    private String nom;

    public Categorie() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(Long id_categorie) {
        this.id_categorie = id_categorie;
    }

    public Categorie(Long id_categorie, String nom) {
        this.id_categorie = id_categorie;
        this.nom = nom;
    }

    public Categorie(String nom) {
        this.nom = nom;
    }
}
