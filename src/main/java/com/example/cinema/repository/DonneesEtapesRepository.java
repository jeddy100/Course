package com.example.cinema.repository;

import com.example.cinema.imp.DonneesEtapes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DonneesEtapesRepository extends JpaRepository<DonneesEtapes, Long> {
    @Modifying
    @Query(value = " insert into etape( etat,longueur, nb_coureur_equipe, nom, rang_etape, heuredepart, date_etape) SELECT 1,cast(longueur as double precision),cast(nbcoureur as bigint),nom_etape,cast(rang as integer),heure_depart,date_depart from donnees_etapes",nativeQuery = true)
    void insertEtapeFromSelect();
}