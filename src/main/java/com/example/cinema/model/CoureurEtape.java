package com.example.cinema.model;

import jakarta.persistence.*;

@Entity
public class CoureurEtape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coureur_etape", nullable = false)
    private Long id_coureurEtape;
    @ManyToOne
    @JoinColumn(name = "coureur_id_coureur")
    private Coureur coureur;

    @ManyToOne
    @JoinColumn(name = "etape_id_etape")
    private Etape etape;

    public CoureurEtape() {

    }

    public Etape getEtape() {
        return etape;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
    }

    public Coureur getCoureur() {
        return coureur;
    }

    public void setCoureur(Coureur coureur) {
        this.coureur = coureur;
    }

    public Long getId_coureurEtape() {
        return id_coureurEtape;
    }

    public void setId_coureurEtape(Long id_coureurEtape) {
        this.id_coureurEtape = id_coureurEtape;
    }

    public CoureurEtape(Long id_coureurEtape, Coureur coureur, Etape etape) {
        this.id_coureurEtape = id_coureurEtape;
        this.coureur = coureur;
        this.etape = etape;
    }

    public CoureurEtape(Coureur coureur, Etape etape) {
        this.coureur = coureur;
        this.etape = etape;
    }
}
