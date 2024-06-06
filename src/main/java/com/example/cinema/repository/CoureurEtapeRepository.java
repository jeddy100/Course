package com.example.cinema.repository;

import com.example.cinema.model.CoureurEtape;
import com.example.cinema.model.Etape;
import com.example.cinema.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoureurEtapeRepository extends JpaRepository<CoureurEtape, Long> {

    @Query("SELECT COUNT(ce) FROM CoureurEtape ce WHERE ce.coureur.utilisateur = :utilisateur AND ce.etape = :etape")
    int countCoureurEtapesByUtilisateurAndEtape(@Param("utilisateur") Utilisateur utilisateur, @Param("etape") Etape etape);

    List<CoureurEtape>getCoureurEtapeByEtape(Etape etape);


    @Modifying
    @Query(value = "insert into coureur_etape(coureur_id_coureur, etape_id_etape) SELECT distinct\n" +
            "c.id_coureur,e.id_etape\n" +
            "                                                                  from donnees_resultat\n" +
            "join public.etape e on cast(donnees_resultat.etape_rang as integer) = e.rang_etape\n" +
            "join public.coureur c on donnees_resultat.nom = c.nom",nativeQuery = true)
    void insertCourreuretapeFromSelect();

    @Query("SELECT ce FROM CoureurEtape ce WHERE ce.etape.id_etape = :etapeId AND ce.coureur.utilisateur = :utilisateur")
    List<CoureurEtape> findByEtapeAndUtilisateur(@Param("etapeId") Long etapeId, @Param("utilisateur") Utilisateur utilisateur);

}