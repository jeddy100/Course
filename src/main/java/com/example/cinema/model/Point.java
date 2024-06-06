package com.example.cinema.model;

import jakarta.persistence.*;

@Entity
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_point", nullable = false)
    private Long id_point;

    private int classement;
    private double nbPoint;

    public Point() {

    }

    public int getClassement() {
        return classement;
    }

    public void setClassement(int classement) {
        this.classement = classement;
    }

    public double getNbPoint() {
        return nbPoint;
    }

    public void setNbPoint(double nbPoint) {
        this.nbPoint = nbPoint;
    }

    public Long getId_point() {
        return id_point;
    }

    public void setId_point(Long id_point) {
        this.id_point = id_point;
    }

    public Point(Long id_point, int classement, double nbPoint) {
        this.id_point = id_point;
        this.classement = classement;
        this.nbPoint = nbPoint;
    }

    public Point(int classement, double nbPoint) {
        this.classement = classement;
        this.nbPoint = nbPoint;
    }
}
