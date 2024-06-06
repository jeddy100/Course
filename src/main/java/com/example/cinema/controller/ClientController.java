package com.example.cinema.controller;

import com.example.cinema.model.*;
import com.example.cinema.repository.CoureurEtapeRepository;
import com.example.cinema.repository.CoureurRepository;
import com.example.cinema.repository.EtapeRepository;
import com.example.cinema.service.CustomUsersDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class ClientController {

    private final EtapeRepository etapeRepository;
    private final CoureurRepository coureurRepository;
    private final CoureurEtapeRepository coureurEtapeRepository;
    private final TempsCoureurEtapeRepository tempsCoureurEtapeRepository;

    public ClientController(EtapeRepository etapeRepository,
                            CoureurRepository coureurRepository,
                            CoureurEtapeRepository coureurEtapeRepository, TempsCoureurEtapeRepository tempsCoureurEtapeRepository) {
        this.etapeRepository = etapeRepository;
        this.coureurRepository = coureurRepository;
        this.coureurEtapeRepository = coureurEtapeRepository;
        this.tempsCoureurEtapeRepository = tempsCoureurEtapeRepository;
    }

    @GetMapping("/listeEtapes")
    public ModelAndView ho(Model model) {
        ModelAndView modelAndView = new ModelAndView("pages/listeEtapes");
        List<Etape> etapeList = etapeRepository.findAll();
        Utilisateur utilisateur = CustomUsersDetailsService.getAuthenticatedUser();  // Obtenez l'utilisateur authentifié

        // Ajouter la liste des CoureurEtape pour chaque étape par utilisateur
        Map<Long, List<TempsCoureurEtape>> tempsParEtape = new HashMap<>();
        for (Etape etape : etapeList) {
            List<CoureurEtape> coureurEtapes = coureurEtapeRepository.findByEtapeAndUtilisateur(etape.getId_etape(), utilisateur);
            List<TempsCoureurEtape> tempsCoureurEtapes = new ArrayList<>();
            for (CoureurEtape ce : coureurEtapes) {
                Optional<TempsCoureurEtape> temps = tempsCoureurEtapeRepository.findByCoureurEtape(ce);
                temps.ifPresent(tempsCoureurEtapes::add);
            }
            tempsParEtape.put(etape.getId_etape(), tempsCoureurEtapes);
        }

        model.addAttribute("etapeList", etapeList);
        model.addAttribute("tempsParEtape", tempsParEtape);
        return modelAndView;
    }


    @GetMapping({"/ajouterCoureur/{id}"})
    public ModelAndView listDevis(@PathVariable Long id, Model model){
        ModelAndView modelAndView=new ModelAndView("pages/ajouterCoureur");
        Optional<Etape> etapeOptional=etapeRepository.findById(id);
        Etape etape=new Etape();
        if(etapeOptional.isPresent()){
            etape=etapeOptional.get();
        }
        Utilisateur utilisateur= CustomUsersDetailsService.getAuthenticatedUser();
        List<Coureur>coureurList=coureurRepository.findCoureursNotInCoureurEtapesByUtilisateurAndEtape(utilisateur, etape);
        int nbcoureurUtilisateur=coureurEtapeRepository.countCoureurEtapesByUtilisateurAndEtape(utilisateur,etape);
        double limiteJoueurs=etape.getNbCoureurEquipe();
        boolean canAddCoureur = nbcoureurUtilisateur < limiteJoueurs;

        model.addAttribute("canAddCoureur", canAddCoureur);
        model.addAttribute("nbcoureur",nbcoureurUtilisateur);
        model.addAttribute("list",coureurList);
        model.addAttribute("etape",etape);



        return modelAndView;
    }

    @PostMapping("/insererCoureurEtape")
    public ModelAndView inscrire(@RequestParam HashMap<String,Object> obj){
        ModelAndView modelAndView=new ModelAndView("redirect:/home");
        Optional<Etape>etapeOptional=etapeRepository.findById(Long.valueOf((String) obj.get("etape")));
        Optional<Coureur>coureurOptional=coureurRepository.findById(Long.valueOf((String) obj.get("coureur")));
        Etape etape=new Etape();
        Coureur coureur=new Coureur();
        if (etapeOptional.isPresent() && coureurOptional.isPresent()){
            etape=etapeOptional.get();
            coureur=coureurOptional.get();
        }
        CoureurEtape coureurEtape=new CoureurEtape(coureur,etape);
        coureurEtapeRepository.save(coureurEtape);


        return modelAndView;

    }
}
