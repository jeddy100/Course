package com.example.cinema.repository;

import com.example.cinema.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Modifying
    @Query(value = "insert into genre(genre)select distinct genre from donnees_resultat",nativeQuery = true)
    void insertGenreFromSelect();

    @Query("select g from Genre g where g.genre = ?1")
    Genre getGenreByGenre(String genre);
}