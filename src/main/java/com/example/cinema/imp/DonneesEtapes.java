package com.example.cinema.imp;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class DonneesEtapes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    String nomEtape;
    String longueur;

    String nbcoureur;
    String rang;
    LocalDate dateDepart;
    LocalTime heureDepart;

    public DonneesEtapes() {

    }

    public String getNbcoureur() {
        return nbcoureur;
    }

    public void setNbcoureur(String nbcoureur) {
        this.nbcoureur = nbcoureur;
    }

    public String getNomEtape() {
        return nomEtape;
    }

    public void setNomEtape(String nomEtape) {
        this.nomEtape = nomEtape;
    }

    public String getLongueur() {
        return longueur;
    }

    public void setLongueur(String longueur) {
        this.longueur = longueur;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DonneesEtapes(Long id, String nomEtape, String longueur, String nbcoureur, String rang, LocalDate dateDepart, LocalTime heureDepart) {
        this.id = id;
        this.nomEtape = nomEtape;
        this.longueur = longueur;
        this.nbcoureur = nbcoureur;
        this.rang = rang;
        this.dateDepart = dateDepart;
        this.heureDepart = heureDepart;
    }

    public DonneesEtapes(String nomEtape, String longueur, String nbcoureur, String rang, LocalDate dateDepart, LocalTime heureDepart) {
        this.nomEtape = nomEtape;
        this.longueur = longueur;
        this.nbcoureur = nbcoureur;
        this.rang = rang;
        this.dateDepart = dateDepart;
        this.heureDepart = heureDepart;
    }
}
