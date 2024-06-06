package com.example.cinema.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Penalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_penalite", nullable = false)
    private Long id_penalite;

    @ManyToOne
    @JoinColumn(name = "etape_id_etape")
    private Etape etape;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    private LocalTime tempsPenalite;
    private int Etat;

    public Penalite() {

    }

    public LocalTime getTempsPenalite() {
        return tempsPenalite;
    }

    public void setTempsPenalite(LocalTime tempsPenalite) {
        this.tempsPenalite = tempsPenalite;
    }

    public int getEtat() {
        return Etat;
    }

    public void setEtat(int etat) {
        Etat = etat;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Etape getEtape() {
        return etape;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
    }

    public Long getId_penalite() {
        return id_penalite;
    }

    public void setId_penalite(Long id_penalite) {
        this.id_penalite = id_penalite;
    }

    public Penalite(Long id_penalite, Etape etape, Utilisateur utilisateur, LocalTime tempsPenalite, int etat) {
        this.id_penalite = id_penalite;
        this.etape = etape;
        this.utilisateur = utilisateur;
        this.tempsPenalite = tempsPenalite;
        Etat = etat;
    }

    public Penalite(Etape etape, Utilisateur utilisateur, LocalTime tempsPenalite, int etat) {
        this.etape = etape;
        this.utilisateur = utilisateur;
        this.tempsPenalite = tempsPenalite;
        Etat = etat;
    }
}
