package com.example.cinema.controller;

import com.example.cinema.model.Etape;
import com.example.cinema.model.Penalite;
import com.example.cinema.model.Utilisateur;
import com.example.cinema.repository.EtapeRepository;
import com.example.cinema.repository.PenaliteRepository;
import com.example.cinema.repository.UserRepository;
import com.example.cinema.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
public class PenaliteController {

    private final UserRepository userRepository;
    private final EtapeRepository etapeRepository;
    private final PenaliteRepository penaliteRepository;

    public PenaliteController(UserRepository userRepository,
                              EtapeRepository etapeRepository,
                              PenaliteRepository penaliteRepository) {
        this.userRepository = userRepository;
        this.etapeRepository = etapeRepository;
        this.penaliteRepository = penaliteRepository;
    }

    @GetMapping("/insertPenalite")
    public ModelAndView penalite(Model model){
        ModelAndView modelAndView=new ModelAndView("pages/penalite");
        List<Utilisateur> utilisateurList=userRepository.getUtilisateurClient();
        List<Etape>etapeList=etapeRepository.findAll();
        model.addAttribute("utilisateurList",utilisateurList);
        model.addAttribute("etapeList",etapeList);


        return modelAndView;

    }

    @PostMapping("/penalitePost")
    public ModelAndView insertPenalite(@RequestParam HashMap<String,Object> obj){
        ModelAndView modelAndView=new ModelAndView("redirect:/ListePenalite");
        Optional<Etape>optionalEtape=etapeRepository.findById(Long.valueOf((String) obj.get("etape")));
        Optional<Utilisateur>optionalUtilisateur=userRepository.findById(Long.valueOf((String) obj.get("utilisateur")));
        Etape etape=new Etape();
        Utilisateur utilisateur=new Utilisateur();
        if (optionalEtape.isPresent() && optionalUtilisateur.isPresent()){
            etape=optionalEtape.get();
            utilisateur=optionalUtilisateur.get();
        }
        String time=String.valueOf(obj.get("temps"));
        LocalTime tempsPenalite= Utils.convertToTime(time);
        int etat=1;
        Penalite penalite=new Penalite(etape,utilisateur,tempsPenalite,etat);
        penaliteRepository.save(penalite);



        return modelAndView;

    }
    @GetMapping("/ListePenalite")
    public ModelAndView listepenalite(Model model){
        ModelAndView modelAndView=new ModelAndView("pages/listepenalite");
        List<Penalite>penaliteList=penaliteRepository.getPenalitesByEtat(1);

        model.addAttribute("penaliteList",penaliteList);
        return modelAndView;

    }
    @PostMapping("/supprimerPenalite")
    public ModelAndView supprimerPenalite(Model model, @RequestParam HashMap<String,Object> obj){
        ModelAndView modelAndView=new ModelAndView("redirect:ListePenalite");
        Optional<Penalite>optionalPenalite=penaliteRepository.findById(Long.valueOf((String) obj.get("id")));
        Penalite penalite=new Penalite();
        if(optionalPenalite.isPresent()){
            penalite=optionalPenalite.get();
        }
        penaliteRepository.delete(penalite);
        return modelAndView;

    }

}
