package com.example.cinema.model;

import jakarta.persistence.*;

@Entity
public class CategorieCoureur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "coureur_id_coureur")
    private Coureur coureur;

    public CategorieCoureur() {

    }

    public Coureur getCoureur() {
        return coureur;
    }

    public void setCoureur(Coureur coureur) {
        this.coureur = coureur;
    }


    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategorieCoureur(Long id, Categorie categorie, Coureur coureur) {
        this.id = id;
        this.categorie = categorie;
        this.coureur = coureur;
    }

    public CategorieCoureur(Categorie categorie, Coureur coureur) {
        this.categorie = categorie;
        this.coureur = coureur;
    }
}
