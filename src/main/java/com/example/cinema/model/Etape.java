package com.example.cinema.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Etape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etape", nullable = false)
    private Long id_etape;
    private String nom;
    private double longueur;
    private double nbCoureurEquipe;
    private int rangEtape;
    private int etat;
    private LocalTime Heuredepart;

    private LocalDate dateEtape;

    public LocalDate getDateEtape() {
        return dateEtape;
    }

    public void setDateEtape(LocalDate dateEtape) {
        this.dateEtape = dateEtape;
    }

    public LocalTime getHeuredepart() {
        return Heuredepart;
    }

    public void setHeuredepart(LocalTime heuredepart) {
        Heuredepart = heuredepart;
    }

    public Etape() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public double getNbCoureurEquipe() {
        return nbCoureurEquipe;
    }

    public void setNbCoureurEquipe(double nbCoureurEquipe) {
        this.nbCoureurEquipe = nbCoureurEquipe;
    }

    public int getRangEtape() {
        return rangEtape;
    }

    public void setRangEtape(int rangEtape) {
        this.rangEtape = rangEtape;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Long getId_etape() {
        return id_etape;
    }

    public void setId_etape(Long id_etape) {
        this.id_etape = id_etape;
    }

    public Etape(Long id_etape, String nom, double longueur, double nbCoureurEquipe, int rangEtape, int etat) {
        this.id_etape = id_etape;
        this.nom = nom;
        this.longueur = longueur;
        this.nbCoureurEquipe = nbCoureurEquipe;
        this.rangEtape = rangEtape;
        this.etat = etat;
    }

    public Etape(String nom, double longueur, double nbCoureurEquipe, int rangEtape, int etat) {
        this.nom = nom;
        this.longueur = longueur;
        this.nbCoureurEquipe = nbCoureurEquipe;
        this.rangEtape = rangEtape;
        this.etat = etat;
    }

    public Etape(Long id_etape, String nom, double longueur, double nbCoureurEquipe, int rangEtape, int etat, LocalTime heuredepart, LocalDate dateEtape) {
        this.id_etape = id_etape;
        this.nom = nom;
        this.longueur = longueur;
        this.nbCoureurEquipe = nbCoureurEquipe;
        this.rangEtape = rangEtape;
        this.etat = etat;
        Heuredepart = heuredepart;
        this.dateEtape = dateEtape;
    }

    public Etape(String nom, double longueur, double nbCoureurEquipe, int rangEtape, int etat, LocalTime heuredepart, LocalDate dateEtape) {
        this.nom = nom;
        this.longueur = longueur;
        this.nbCoureurEquipe = nbCoureurEquipe;
        this.rangEtape = rangEtape;
        this.etat = etat;
        Heuredepart = heuredepart;
        this.dateEtape = dateEtape;
    }
}
