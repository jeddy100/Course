package com.example.cinema.object;

import com.example.cinema.model.Utilisateur;

public class RankingPoint {
    long id;
    Utilisateur utilisateur;
    double point;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public RankingPoint(Utilisateur utilisateur, double point) {
        this.utilisateur = utilisateur;
        this.point = point;
    }

    public RankingPoint(long id, Utilisateur utilisateur, double point) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.point = point;
    }
    public RankingPoint(){}
}
