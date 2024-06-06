package com.example.cinema.model;

import jakarta.persistence.*;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nom;
    private String password;
    private String role;

    private String nomEquipe;

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setTelephone(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Utilisateur() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Utilisateur(Long id, String nom, String password,String role) {
        this.id = id;
        this.nom = nom;
        this.password = password;
        this.role= role;

    }

    public Utilisateur(String nom, String password,String role) {
        this.nom = nom;
        this.password = password;
        this.role= role;
    }

    public Utilisateur(Long id, String nom, String password, String role, String nomEquipe) {
        this.id = id;
        this.nom = nom;
        this.password = password;
        this.role = role;
        this.nomEquipe = nomEquipe;
    }

    public Utilisateur(String nom, String password, String role, String nomEquipe) {
        this.nom = nom;
        this.password = password;
        this.role = role;
        this.nomEquipe = nomEquipe;
    }

}
