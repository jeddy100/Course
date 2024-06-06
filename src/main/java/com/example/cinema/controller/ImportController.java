package com.example.cinema.controller;

import com.example.cinema.imp.DonneesEtapes;
import com.example.cinema.imp.DonneesPoint;
import com.example.cinema.imp.DonneesResultat;
import com.example.cinema.model.CoureurEtape;
import com.example.cinema.model.TempsCoureurEtape;
import com.example.cinema.model.TempsCoureurEtapeRepository;
import com.example.cinema.model.Utilisateur;
import com.example.cinema.repository.*;
import com.example.cinema.service.ImportEtapeService;
import com.example.cinema.utils.Utils;
import jakarta.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static com.example.cinema.utils.Utils.*;

@Controller
public class ImportController {

    private final DonneesEtapesRepository donneesEtapesRepository;
    private final ImportEtapeService importEtapeService;
    private final DonneesPointRepository donneesPointRepository;
    private final DonneesResultatRepository donneesResultatRepository;
    private final CoureurEtapeRepository coureurEtapeRepository;
    private final TempsCoureurEtapeRepository tempsCoureurEtapeRepository;
    private final PenaliteRepository penaliteRepository;
    private final PointRepository pointRepository;
    private final EtapeRepository etapeRepository;
    private final CategorieCoureurRepository categorieCoureurRepository;
    private final CategorieRepository categorieRepository;
    private final CoureurRepository coureurRepository;
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;

    public ImportController(DonneesEtapesRepository donneesEtapesRepository, ImportEtapeService importEtapeService,
                            DonneesPointRepository donneesPointRepository,
                            DonneesResultatRepository donneesResultatRepository,
                            CoureurEtapeRepository coureurEtapeRepository,
                            TempsCoureurEtapeRepository tempsCoureurEtapeRepository,
                            PenaliteRepository penaliteRepository,
                            PointRepository pointRepository,
                            EtapeRepository etapeRepository,
                            CategorieCoureurRepository categorieCoureurRepository,
                            CategorieRepository categorieRepository,
                            CoureurRepository coureurRepository,
                            UserRepository userRepository,
                            GenreRepository genreRepository) {
        this.donneesEtapesRepository = donneesEtapesRepository;
        this.importEtapeService = importEtapeService;
        this.donneesPointRepository = donneesPointRepository;
        this.donneesResultatRepository = donneesResultatRepository;
        this.coureurEtapeRepository = coureurEtapeRepository;
        this.tempsCoureurEtapeRepository = tempsCoureurEtapeRepository;
        this.penaliteRepository = penaliteRepository;
        this.pointRepository = pointRepository;
        this.etapeRepository = etapeRepository;
        this.categorieCoureurRepository = categorieCoureurRepository;
        this.categorieRepository = categorieRepository;
        this.coureurRepository = coureurRepository;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
    }

    @PostMapping("/importdonneespost")
    // type de retour pour un insert RedirectView
    public RedirectView newEtudiant(@RequestPart("file") MultipartFile multipartFile) throws IOException {

        Scanner scanner=new Scanner(multipartFile.getInputStream());
        List<List<String>> records = new ArrayList<>();
        while (scanner.hasNextLine()) {
//            records.add(Utils.getRecordFromLine(scanner.nextLine()));
            records.add(Utils.getRecordFromLine(scanner.nextLine()));
        }
        records.remove(0);
        records.forEach(System.out::println);

        for (int i = 0; i <records.size() ; i++) {
            String nometape=records.get(i).get(0);
            String longueur=records.get(i).get(1);
            String nbcoureur=records.get(i).get(2);
            String rang=records.get(i).get(3);
            LocalDate date= parseAnyStringToLocalDate(records.get(i).get(4));
            LocalTime time=  convertToTime(records.get(i).get(5));
            DonneesEtapes donneesEtapes=new DonneesEtapes(nometape,longueur,nbcoureur,rang,date,time);
            donneesEtapesRepository.save(donneesEtapes);
        }
        importEtapeService.insererDonnees();



        final RedirectView redirectView = new RedirectView("redirect:/home", true);

        return redirectView;
    }

    @PostMapping("/importdonneespointpost")
    // type de retour pour un insert RedirectView
    public RedirectView newpoint(@RequestPart("file") MultipartFile multipartFile) throws IOException {

        Scanner scanner=new Scanner(multipartFile.getInputStream());
        List<List<String>> records = new ArrayList<>();
        while (scanner.hasNextLine()) {
//            records.add(Utils.getRecordFromLine(scanner.nextLine()));
            records.add(Utils.getRecordFromLine(scanner.nextLine()));
        }
        records.remove(0);
        records.forEach(System.out::println);

        for (int i = 0; i <records.size() ; i++) {
            String classement=records.get(i).get(0);
            String point=records.get(i).get(1);

            DonneesPoint donneesPoint=new DonneesPoint(classement,point);
           donneesPointRepository.save(donneesPoint);
        }
        importEtapeService.insererDonneesPoint();



        final RedirectView redirectView = new RedirectView("redirect:/home", true);

        return redirectView;
    }

    @PostMapping("/importdonneesresultatpost")
    // type de retour pour un insert RedirectView
    public RedirectView newresultat(@RequestPart("file") MultipartFile multipartFile) throws IOException {

        Scanner scanner=new Scanner(multipartFile.getInputStream());
        List<List<String>> records = new ArrayList<>();
        while (scanner.hasNextLine()) {
//            records.add(Utils.getRecordFromLine(scanner.nextLine()));
            records.add(Utils.getRecordFromLine(scanner.nextLine()));
        }
        records.remove(0);
        records.forEach(System.out::println);

        for (int i = 0; i <records.size() ; i++) {
            String etape_rang =records.get(i).get(0);
            String numero_dossard=records.get(i).get(1);
            String nom=records.get(i).get(2);
            String genre=records.get(i).get(3);
            LocalDate date_naissance=parseAnyStringToLocalDate(records.get(i).get(4));
            String equipe=records.get(i).get(5);
            LocalDateTime arrivvee=parseAnyStringToLocalDateTime(records.get(i).get(6));

            DonneesResultat donneesResultat=new DonneesResultat(etape_rang,numero_dossard,nom,genre,date_naissance,equipe,arrivvee);
            donneesResultatRepository.save(donneesResultat);
        }
        importEtapeService.insererDonnesResultat();




        final RedirectView redirectView = new RedirectView("redirect:/home", true);

        return redirectView;
    }
    @PostMapping("/resetdonnees")
    // type de retour pour un insert RedirectView
    public RedirectView resetdonnees() throws IOException {
        final RedirectView redirectView = new RedirectView("redirect:/home", true);
        tempsCoureurEtapeRepository.deleteAll();
        coureurEtapeRepository.deleteAll();
        penaliteRepository.deleteAll();
        donneesResultatRepository.deleteAll();
        donneesEtapesRepository.deleteAll();
        donneesPointRepository.deleteAll();
        pointRepository.deleteAll();
        etapeRepository.deleteAll();
        categorieCoureurRepository.deleteAll();
        coureurEtapeRepository.deleteAll();
//        categorieRepository.deleteAll();
        coureurRepository.deleteAll();
        Utilisateur utilisateur=userRepository.getUtilisateurAdmin();
        userRepository.deleteAll();
        genreRepository.deleteAll();
        userRepository.save(utilisateur);
        System.out.println("ok");
        return redirectView;
    }



    }
