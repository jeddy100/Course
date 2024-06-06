package com.example.cinema.repository;

import com.example.cinema.model.Coureur;
import com.example.cinema.model.Etape;
import com.example.cinema.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoureurRepository extends JpaRepository<Coureur, Long> {
        //DENSE_RANK pour les mitovy
    public List<Coureur> getAllByUtilisateur(Utilisateur utilisateur);
    @Query("SELECT c FROM Coureur c WHERE c.utilisateur = :utilisateur AND c.id_coureur NOT IN (SELECT ce.coureur.id_coureur FROM CoureurEtape ce WHERE ce.etape = :etape)")
    List<Coureur> findCoureursNotInCoureurEtapesByUtilisateurAndEtape(@Param("utilisateur") Utilisateur utilisateur, @Param("etape") Etape etape);

    @Query(value = "SELECT c.id_coureur, c.nom, ce.etape_id_etape, e.nom AS etape_nom, t.heuredebut, t.heurefin, EXTRACT(EPOCH FROM CASE WHEN t.heurefin >= t.heuredebut THEN t.heurefin - t.heuredebut ELSE t.heurefin - t.heuredebut + interval '24:00:00' END) AS temps_total,  RANK() OVER (ORDER BY\n" +
            "        CASE WHEN t.heurefin >= t.heuredebut THEN t.heurefin - t.heuredebut\n" +
            "        ELSE t.heurefin - t.heuredebut + interval '24:00:00' END) AS classement FROM Coureur c JOIN coureur_etape ce ON c.id_coureur = ce.coureur_id_coureur JOIN Etape e ON ce.etape_id_etape = e.id_etape JOIN temps_coureur_etape t ON ce.id_coureur_etape = t.coureur_etape_id_coureur_etape WHERE e.id_etape = :etapeId ORDER BY temps_total ASC", nativeQuery = true)
    List<Object[]> findClassementByEtapeId(@Param("etapeId") Long etapeId);

//    @Query(value = "SELECT t.id_temps_coureur_etape, EXTRACT(EPOCH FROM CASE WHEN t.heurefin >= t.heuredebut THEN t.heurefin - t.heuredebut ELSE t.heurefin - t.heuredebut + interval '24:00:00' END) AS temps_total,  DENSE_RANK() OVER (ORDER BY\n" +
//            "        CASE WHEN t.heurefin >= t.heuredebut THEN t.heurefin - t.heuredebut\n" +
//            "        ELSE t.heurefin - t.heuredebut END) AS classement FROM Coureur c JOIN coureur_etape ce ON c.id_coureur = ce.coureur_id_coureur JOIN Etape e ON ce.etape_id_etape = e.id_etape JOIN temps_coureur_etape t ON ce.id_coureur_etape = t.coureur_etape_id_coureur_etape WHERE e.id_etape = :etapeId ORDER BY temps_total ASC", nativeQuery = true)
//    List<Object[]> findClassementByEtapeId2(@Param("etapeId") Long etapeId);

    @Query(value = "select id_temps_coureur_etape,temps_total,classement,pen from v_classement_par_etape where etape_id_etape = :etapeId and id_temps_coureur_etape IS NOT NULL", nativeQuery = true)
    List<Object[]> findClassementByEtapeId2(@Param("etapeId") Long etapeId);


    @Modifying
    @Query(value = "insert into coureur(date_de_naissance, nom, num_dorssard, id_genre, utilisateur_id) SELECT Distinct\n" +
            "date_naissance,donnees_resultat.nom,cast(numero_dossard as integer),g.id_genre,u.id\n" +
            "                                                                                    from donnees_resultat\n" +
            "join public.genre g on donnees_resultat.genre = g.genre\n" +
            "join public.utilisateur u on donnees_resultat.equipe = u.nom",nativeQuery = true)
    void insertCoureurFromSelect();


///////////////////////////par categorie
//    @Query(value = "SELECT t.id_temps_coureur_etape, EXTRACT(EPOCH FROM CASE WHEN t.heurefin >= t.heuredebut THEN t.heurefin - t.heuredebut ELSE t.heurefin - t.heuredebut + interval '24:00:00' END) AS temps_total,  DENSE_RANK() OVER (ORDER BY\n" +
//            "        CASE WHEN t.heurefin >= t.heuredebut THEN t.heurefin - t.heuredebut\n" +
//            "        ELSE t.heurefin - t.heuredebut END) AS classement FROM Coureur c JOIN coureur_etape ce ON c.id_coureur = ce.coureur_id_coureur JOIN Etape e ON ce.etape_id_etape = e.id_etape JOIN temps_coureur_etape t ON ce.id_coureur_etape = t.coureur_etape_id_coureur_etape Join public.categorie_coureur cc on c.id_coureur = cc.coureur_id_coureur WHERE e.id_etape = :etapeId  and cc.id_categorie= :categorieId ORDER BY temps_total ASC", nativeQuery = true)

    @Query(value = "select id_temps_coureur_etape,temps_total,classement from v_classement_par_etape_par_categorie where etape_id_etape = :etapeId and id_categorie = :categorieId and id_temps_coureur_etape IS NOT NULL", nativeQuery = true)
    List<Object[]> findClassementByEtapeIdAndCategorie(@Param("etapeId") Long etapeId,Long categorieId);
}