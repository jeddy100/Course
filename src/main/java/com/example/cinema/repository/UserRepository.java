package com.example.cinema.repository;

import com.example.cinema.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Utilisateur, Long> {
    public Utilisateur getUserByNom(String nom);
    @Modifying
    @Query(value = "insert into utilisateur(nom, nom_equipe, password, role)  SELECT distinct equipe,equipe,'$2a$10$rvs2zqmvy4P5.SCfRqpAg.jMr/XepDg0lZOtBkGvZiIdY.d51He12','client' from donnees_resultat",nativeQuery = true)
    void insertUtilisateurFromSelect();


    @Query("select u from Utilisateur u where u.role ='client' ")
    List<Utilisateur> getUtilisateurClient();
    @Query("select u from Utilisateur u where u.role ='admin' ")
    Utilisateur getUtilisateurAdmin();
}