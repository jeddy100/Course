package com.example.cinema.service;

import com.example.cinema.model.TempsCoureurEtapeRepository;
import com.example.cinema.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImportEtapeService {

    private final DonneesEtapesRepository donneesEtapesRepository;
    private final DonneesPointRepository donneesPointRepository;
    private final GenreRepository genreRepository;
    private final UserRepository userRepository;
    private final CoureurRepository coureurRepository;
    private final CoureurEtapeRepository coureurEtapeRepository;
    private final TempsCoureurEtapeRepository tempsCoureurEtapeRepository;

    public ImportEtapeService(DonneesEtapesRepository donneesEtapesRepository,
                              DonneesPointRepository donneesPointRepository,
                              GenreRepository genreRepository,
                              UserRepository userRepository,
                              CoureurRepository coureurRepository,
                              CoureurEtapeRepository coureurEtapeRepository,
                              TempsCoureurEtapeRepository tempsCoureurEtapeRepository) {
        this.donneesEtapesRepository = donneesEtapesRepository;
        this.donneesPointRepository = donneesPointRepository;
        this.genreRepository = genreRepository;
        this.userRepository = userRepository;
        this.coureurRepository = coureurRepository;
        this.coureurEtapeRepository = coureurEtapeRepository;
        this.tempsCoureurEtapeRepository = tempsCoureurEtapeRepository;
    }

    @Transactional
    public void insererDonnees() {
        donneesEtapesRepository.insertEtapeFromSelect();
        System.out.println("donnees etapes enregistrees");
    }

    @Transactional
    public void insererDonneesPoint() {
        donneesPointRepository.insertPointsFromSelect();
        System.out.println("donnees points enregistrees");
    }

    @Transactional
    public void insererDonnesResultat() {
        //insertion genre
        genreRepository.insertGenreFromSelect();
        System.out.println("genre ok");
        //insertion equipe
        userRepository.insertUtilisateurFromSelect();
        System.out.println("utilisateur ok");

        //insertion coureur
        coureurRepository.insertCoureurFromSelect();
        System.out.println("coureur ok");

        //insertion coureuretape
        coureurEtapeRepository.insertCourreuretapeFromSelect();
        System.out.println("coureuretape ok");

        //insertion temps coureur
        tempsCoureurEtapeRepository.insertTCEFromSelect();

        System.out.println("donnees resultats enregistrees");
    }
}
