package com.example.cinema.imp;

import jakarta.persistence.*;

@Entity
public class DonneesPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public DonneesPoint() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    String classement;
    String point;

    public String getClassement() {
        return classement;
    }

    public void setClassement(String classement) {
        this.classement = classement;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public DonneesPoint(Long id, String classement, String point) {
        this.id = id;
        this.classement = classement;
        this.point = point;
    }

    public DonneesPoint(String classement, String point) {
        this.classement = classement;
        this.point = point;
    }
}
