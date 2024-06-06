package com.example.cinema.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TempsCoureurEtapeRepository extends JpaRepository<TempsCoureurEtape, Long> {
    Optional<TempsCoureurEtape> findByCoureurEtape(CoureurEtape coureurEtape);

    @Modifying
    @Query(value = "insert into temps_coureur_etape(heuredebut, heurefin, coureur_etape_id_coureur_etape) SELECT\n" +
            "e.date_etape+e.heuredepart as timestamp,arrivee,ce.id_coureur_etape\n" +
            "                                                                                          from donnees_resultat\n" +
            "join public.etape e on cast(donnees_resultat.etape_rang as integer) = e.rang_etape\n" +
            "join public.coureur c on donnees_resultat.nom = c.nom\n" +
            "join public.coureur_etape ce on e.id_etape = ce.etape_id_etape and c.id_coureur = ce.coureur_id_coureur",nativeQuery = true)
    void insertTCEFromSelect();

}