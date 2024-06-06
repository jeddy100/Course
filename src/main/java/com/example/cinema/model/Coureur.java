package com.example.cinema.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Coureur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coureur", nullable = false)
    private Long id_coureur;
    private String nom;
    private int numDorssard;

    @ManyToOne
    @JoinColumn(name = "id_genre")
    private Genre genre;

    private LocalDate dateDeNaissance;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    public Coureur() {

    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Long getId_coureur() {
        return id_coureur;
    }

    public void setId_coureur(Long id_coureur) {
        this.id_coureur = id_coureur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumDorssard() {
        return numDorssard;
    }

    public void setNumDorssard(int numDorssard) {
        this.numDorssard = numDorssard;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public Coureur(Long id_coureur, String nom, int numDorssard, Genre genre, LocalDate dateDeNaissance, Utilisateur utilisateur) {
        this.id_coureur = id_coureur;
        this.nom = nom;
        this.numDorssard = numDorssard;
        this.genre = genre;
        this.dateDeNaissance = dateDeNaissance;
        this.utilisateur = utilisateur;
    }

    public Coureur(String nom, int numDorssard, Genre genre, LocalDate dateDeNaissance, Utilisateur utilisateur) {
        this.nom = nom;
        this.numDorssard = numDorssard;
        this.genre = genre;
        this.dateDeNaissance = dateDeNaissance;
        this.utilisateur = utilisateur;
    }
}
