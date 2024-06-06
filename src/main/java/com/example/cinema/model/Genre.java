package com.example.cinema.model;

import jakarta.persistence.*;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre", nullable = false)
    private Long id_genre;
    private String genre;

    public Genre() {

    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getId_genre() {
        return id_genre;
    }

    public void setId_genre(Long id_genre) {
        this.id_genre = id_genre;
    }

    public Genre(Long id_genre, String genre) {
        this.id_genre = id_genre;
        this.genre = genre;
    }

    public Genre(String genre) {
        this.genre = genre;
    }
}
