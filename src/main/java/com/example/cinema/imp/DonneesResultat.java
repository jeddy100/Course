package com.example.cinema.imp;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class DonneesResultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public DonneesResultat() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//    etape_rang	numero dossard	nom	genre	date naissance	equipe	arrivée
    String etape_rang;
    String numero_dossard;
    String nom;
    String genre;
    LocalDate date_naissance;
    String equipe;
    LocalDateTime arrivee;

    public String getEtape_rang() {
        return etape_rang;
    }

    public void setEtape_rang(String etape_rang) {
        this.etape_rang = etape_rang;
    }

    public String getNumero_dossard() {
        return numero_dossard;
    }

    public void setNumero_dossard(String numero_dossard) {
        this.numero_dossard = numero_dossard;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public LocalDateTime getArrivee() {
        return arrivee;
    }

    public void setArrivee(LocalDateTime arrivee) {
        this.arrivee = arrivee;
    }

    public DonneesResultat(Long id, String etape_rang, String numero_dossard, String nom, String genre, LocalDate date_naissance, String equipe, LocalDateTime arrivee) {
        this.id = id;
        this.etape_rang = etape_rang;
        this.numero_dossard = numero_dossard;
        this.nom = nom;
        this.genre = genre;
        this.date_naissance = date_naissance;
        this.equipe = equipe;
        this.arrivee = arrivee;
    }

    public DonneesResultat(String etape_rang, String numero_dossard, String nom, String genre, LocalDate date_naissance, String equipe, LocalDateTime arrivee) {
        this.etape_rang = etape_rang;
        this.numero_dossard = numero_dossard;
        this.nom = nom;
        this.genre = genre;
        this.date_naissance = date_naissance;
        this.equipe = equipe;
        this.arrivee = arrivee;
    }
}
