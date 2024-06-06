package com.example.cinema.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TempsCoureurEtape  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_temps_coureur_etape", nullable = false)
    private Long id_tempsCoureurEtape;

    @ManyToOne
    @JoinColumn(name = "coureur_etape_id_coureur_etape")
    private CoureurEtape coureurEtape;
    private LocalDateTime heuredebut;
    private LocalDateTime heurefin;

    public TempsCoureurEtape() {

    }

    public LocalDateTime getHeuredebut() {
        return heuredebut;
    }

    public void setHeuredebut(LocalDateTime heuredebut) {
        this.heuredebut = heuredebut;
    }

    public LocalDateTime getHeurefin() {
        return heurefin;
    }

    public void setHeurefin(LocalDateTime heurefin) {
        this.heurefin = heurefin;
    }

    public CoureurEtape getCoureurEtape() {
        return coureurEtape;
    }

    public void setCoureurEtape(CoureurEtape coureurEtape) {
        this.coureurEtape = coureurEtape;
    }

    public Long getId_tempsCoureurEtape() {
        return id_tempsCoureurEtape;
    }

    public void setId_tempsCoureurEtape(Long id_tempsCoureurEtape) {
        this.id_tempsCoureurEtape = id_tempsCoureurEtape;
    }

    public TempsCoureurEtape(Long id_tempsCoureurEtape, CoureurEtape coureurEtape, LocalDateTime heuredebut, LocalDateTime heurefin) {
        this.id_tempsCoureurEtape = id_tempsCoureurEtape;
        this.coureurEtape = coureurEtape;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
    }

    public TempsCoureurEtape(CoureurEtape coureurEtape, LocalDateTime heuredebut, LocalDateTime heurefin) {
        this.coureurEtape = coureurEtape;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
    }
}

