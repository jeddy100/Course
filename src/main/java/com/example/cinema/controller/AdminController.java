package com.example.cinema.controller;

import com.example.cinema.model.*;
import com.example.cinema.object.Ranking;
import com.example.cinema.object.RankingPoint;
import com.example.cinema.object.pointEtape;
import com.example.cinema.repository.*;
import com.example.cinema.service.CourreurService;
import com.example.cinema.service.CustomUsersDetailsService;
import com.example.cinema.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Controller
public class AdminController {

    private final EtapeRepository etapeRepository;
    private final CoureurEtapeRepository coureurEtapeRepository;
    private final TempsCoureurEtapeRepository tempsCoureurEtapeRepository;
    private final CoureurRepository coureurRepository;
    private final CourreurService courreurService;
    private final UserRepository userRepository;
    private final CategorieRepository categorieRepository;

    public AdminController(EtapeRepository etapeRepository,
                           CoureurEtapeRepository coureurEtapeRepository,
                           TempsCoureurEtapeRepository tempsCoureurEtapeRepository,
                           CoureurRepository coureurRepository, CourreurService courreurService,
                           UserRepository userRepository,
                           CategorieRepository categorieRepository) {
        this.etapeRepository = etapeRepository;
        this.coureurEtapeRepository = coureurEtapeRepository;
        this.tempsCoureurEtapeRepository = tempsCoureurEtapeRepository;
        this.coureurRepository = coureurRepository;
        this.courreurService = courreurService;
        this.userRepository = userRepository;
        this.categorieRepository = categorieRepository;
    }

    @GetMapping("/listeEtapesAdmin")
    public ModelAndView ho(Model model){
        ModelAndView modelAndView=new ModelAndView("pages/listeEtapesAdmin");
        List<Etape> etapeList=etapeRepository.findAll();
        model.addAttribute("etapeList",etapeList);
        return modelAndView;

    }

    @GetMapping({"/listeCoureurEtape/{id}"})
    public ModelAndView listDevis(@PathVariable Long id, Model model){
        ModelAndView modelAndView=new ModelAndView("pages/listeCoureurEtape");
        Optional<Etape> etapeOptional=etapeRepository.findById(id);
        Etape etape=new Etape();
        if(etapeOptional.isPresent()){
            etape=etapeOptional.get();
        }
        List<CoureurEtape>coureurEtapeList=coureurEtapeRepository.getCoureurEtapeByEtape(etape);

        model.addAttribute("coureurEtapeList",coureurEtapeList);
        model.addAttribute("etape",etape);



        return modelAndView;
    }
    @GetMapping({"/listeCoureurEtape/ajouterHeureArrivee/{id}"})
    public ModelAndView heure(@PathVariable Long id, Model model){
        ModelAndView modelAndView=new ModelAndView("pages/insererTemps");
        Optional<CoureurEtape>coureurEtapeOptional=coureurEtapeRepository.findById(id);
        CoureurEtape coureurEtape=new CoureurEtape();
        if(coureurEtapeOptional.isPresent()){
            coureurEtape=coureurEtapeOptional.get();
        }
        model.addAttribute("coureurEtape",coureurEtape);



        return modelAndView;
    }

    @PostMapping("/insererTemps")
    public ModelAndView inscrire(@RequestParam HashMap<String,Object> obj){
        ModelAndView modelAndView = new ModelAndView("pages/insererTemps");
        Long coureurEtapeId = Long.valueOf((String) obj.get("coureurEtape"));
        Optional<CoureurEtape> coureurEtapeOptional = coureurEtapeRepository.findById(coureurEtapeId);

        if (coureurEtapeOptional.isPresent()) {
            CoureurEtape coureurEtape = coureurEtapeOptional.get();
            Optional<TempsCoureurEtape> tempsCoureurEtapeOptional = tempsCoureurEtapeRepository.findByCoureurEtape(coureurEtape);

            if (tempsCoureurEtapeOptional.isPresent()) {
                // Temps already exists, set error message
                modelAndView.addObject("errorMessage", "Temps déjà enregistré pour ce coureur.");
            } else {
                // Temps does not exist, save new temps
                LocalDate localDate=coureurEtape.getEtape().getDateEtape();
                LocalTime timeetape=coureurEtape.getEtape().getHeuredepart();
//                String dateheuredebut= String.valueOf(localDate)+" "+String.valueOf(timeetape);
//                System.out.println("temps====="+dateheuredebut);
//                LocalDateTime dateheurefin=Utils.parseAnyStringToLocalDateTime(dateheuredebut);
                LocalDateTime dateheuredebut=LocalDateTime.of(localDate,timeetape);
                String heurefin=String.valueOf(obj.get("temps"));
                System.out.println("heurefin:"+heurefin);
                System.out.println("temps:"+String.valueOf(obj.get("temps")));
                LocalDateTime time= Utils.parseAnyStringToLocalDateTime(String.valueOf(obj.get("temps")));
                TempsCoureurEtape tempsCoureurEtape = new TempsCoureurEtape(coureurEtape, dateheuredebut, time);
                tempsCoureurEtapeRepository.save(tempsCoureurEtape);
            }

            modelAndView.addObject("coureurEtape", coureurEtape);
        }

        return modelAndView;

    }


    @GetMapping("/listeEtapesClassement")
    public ModelAndView classement(Model model){
        ModelAndView modelAndView=new ModelAndView("pages/listeEtapesClassement");
        List<Etape> etapeList=etapeRepository.findAll();
        model.addAttribute("etapeList",etapeList);
        return modelAndView;

    }

    @GetMapping("/classementEtape/{etapeId}")
    public ModelAndView classementEtape(@PathVariable Long etapeId) {
        ModelAndView modelAndView = new ModelAndView("pages/classement");

//       List<Object[]> classement = coureurRepository.findClassementByEtapeId2(etapeId);
//        // Ajouter le classement à l'objet ModelAndView
//        List<Ranking>rankingList=new ArrayList<>();
//        for (int i = 0; i < classement.size() ; i++) {
//            Optional<TempsCoureurEtape>optional=tempsCoureurEtapeRepository.findById((Long) classement.get(i)[0]);
//            TempsCoureurEtape tempsCoureurEtape=new TempsCoureurEtape();
//            if(optional.isPresent()){
//                tempsCoureurEtape=optional.get();
//            }
//            BigDecimal tempstotal= (BigDecimal) classement.get(i)[1];
//            Long rang= (Long) classement.get(i)[2];
//            double point= courreurService.getNbPointsByClassement(rang);
//            System.out.println("rang:"+tempsCoureurEtape.getCoureurEtape().getCoureur().getNom()+" "+point);
//            Ranking ranking=new Ranking(tempsCoureurEtape,tempstotal,rang,point);
//            rankingList.add(ranking);
//
//        }

        /////////////////////////////////////classement general
        List<Ranking>rankingList=courreurService.getClassementEtape(etapeId);

        ////////////////////classement par categorie
        Categorie junior=categorieRepository.getCategorieByNom("junior");
        List<Ranking>rankingListJunior=courreurService.getClassementEtapeCategorie(etapeId, junior.getId_categorie());

        Categorie homme=categorieRepository.getCategorieByNom("homme");
        List<Ranking>rankingListHomme=courreurService.getClassementEtapeCategorie(etapeId,homme.getId_categorie());

        Categorie damme=categorieRepository.getCategorieByNom("damme");
        List<Ranking>rankingListDamme=courreurService.getClassementEtapeCategorie(etapeId, damme.getId_categorie());



        //////////////////////////////////////
        modelAndView.addObject("rankingList", rankingList);
        modelAndView.addObject("rankingListJunior", rankingListJunior);
        modelAndView.addObject("rankingListHomme", rankingListHomme);
        modelAndView.addObject("rankingListDamme", rankingListDamme);

        return modelAndView;
    }


    @GetMapping("/classementGeneral")
    public ModelAndView classementgeneral(Model model){
        ModelAndView modelAndView=new ModelAndView("pages/ClassementGeneral");
       List<RankingPoint>rankingPointList=new ArrayList<>();
        List<Utilisateur>utilisateurList=userRepository.getUtilisateurClient();
        for (int i = 0; i < utilisateurList.size() ; i++) {

            double nbpoints=courreurService.getPointequipeByEtape(utilisateurList.get(i));
            RankingPoint rankingPoint=new RankingPoint(utilisateurList.get(i),nbpoints);
            rankingPointList.add(rankingPoint);
        }
        rankingPointList.sort((rp1, rp2) -> Double.compare(rp2.getPoint(), rp1.getPoint()));

        //////////////////////par categorie
        Categorie junior=categorieRepository.getCategorieByNom("junior");
        List<RankingPoint>rankingPointListJunior=new ArrayList<>();
        for (int i = 0; i < utilisateurList.size() ; i++) {

            double nbpoints=courreurService.getPointequipeByEtapeCategorie(utilisateurList.get(i), junior.getId_categorie());
            RankingPoint rankingPoint=new RankingPoint(utilisateurList.get(i),nbpoints);
            rankingPointListJunior.add(rankingPoint);
        }
        rankingPointListJunior.sort((rp1, rp2) -> Double.compare(rp2.getPoint(), rp1.getPoint()));

        //////////////////////par categorie homme
        Categorie homme=categorieRepository.getCategorieByNom("homme");
        List<RankingPoint>rankingPointListHomme=new ArrayList<>();
        for (int i = 0; i < utilisateurList.size() ; i++) {

            double nbpoints=courreurService.getPointequipeByEtapeCategorie(utilisateurList.get(i), homme.getId_categorie());
            RankingPoint rankingPoint=new RankingPoint(utilisateurList.get(i),nbpoints);
            rankingPointListHomme.add(rankingPoint);
        }
        rankingPointListHomme.sort((rp1, rp2) -> Double.compare(rp2.getPoint(), rp1.getPoint()));

        //////////////////////par categorie damme
        Categorie damme=categorieRepository.getCategorieByNom("damme");
        List<RankingPoint>rankingPointListDamme=new ArrayList<>();
        for (int i = 0; i < utilisateurList.size() ; i++) {

            double nbpoints=courreurService.getPointequipeByEtapeCategorie(utilisateurList.get(i), damme.getId_categorie());
            RankingPoint rankingPoint=new RankingPoint(utilisateurList.get(i),nbpoints);
            rankingPointListDamme.add(rankingPoint);
        }
        rankingPointListDamme.sort((rp1, rp2) -> Double.compare(rp2.getPoint(), rp1.getPoint()));
        ////////////////////total
        List<RankingPoint>rankingPointListTotal=new ArrayList<>();
        for (int i = 0; i < utilisateurList.size() ; i++) {
            double nbpoints=courreurService.getPointequipeByEtapeCategorie(utilisateurList.get(i), damme.getId_categorie())+courreurService.getPointequipeByEtapeCategorie(utilisateurList.get(i), homme.getId_categorie())+courreurService.getPointequipeByEtapeCategorie(utilisateurList.get(i), junior.getId_categorie())+courreurService.getPointequipeByEtape(utilisateurList.get(i));
            RankingPoint rankingPoint=new RankingPoint(utilisateurList.get(i),nbpoints);
            rankingPointListTotal.add(rankingPoint);
        }
        rankingPointListTotal.sort((rp1, rp2) -> Double.compare(rp2.getPoint(), rp1.getPoint()));




        model.addAttribute("rankingPointList",rankingPointList);
        model.addAttribute("rankingPointListDamme",rankingPointListDamme);
        model.addAttribute("rankingPointListHomme",rankingPointListHomme);
        model.addAttribute("rankingPointListJunior",rankingPointListJunior);
        model.addAttribute("rankingPointListTotal",rankingPointListTotal);
        return modelAndView;

    }

    @GetMapping("/certificat")
    public ModelAndView certificat(Model model){
        ModelAndView modelAndView=new ModelAndView("pages/certificat");
        List<Utilisateur>utilisateurList=userRepository.getUtilisateurClient();
        Categorie homme=categorieRepository.getCategorieByNom("homme");
        Categorie damme=categorieRepository.getCategorieByNom("damme");
        Categorie junior=categorieRepository.getCategorieByNom("junior");

        List<RankingPoint>rankingPointListTotal=new ArrayList<>();
        for (int i = 0; i < utilisateurList.size() ; i++) {
            double nbpoints=courreurService.getPointequipeByEtapeCategorie(utilisateurList.get(i), damme.getId_categorie())+courreurService.getPointequipeByEtapeCategorie(utilisateurList.get(i), homme.getId_categorie())+courreurService.getPointequipeByEtapeCategorie(utilisateurList.get(i), junior.getId_categorie())+courreurService.getPointequipeByEtape(utilisateurList.get(i));
            RankingPoint rankingPoint=new RankingPoint(utilisateurList.get(i),nbpoints);
            rankingPointListTotal.add(rankingPoint);
        }
        rankingPointListTotal.sort((rp1, rp2) -> Double.compare(rp2.getPoint(), rp1.getPoint()));

        model.addAttribute("gagnant",rankingPointListTotal.get(0));
    return modelAndView;


    }

    @GetMapping("/pointEquipeEtape/{id}")
    public ModelAndView pointetapeequipe(@PathVariable Long id,Model model){
        ModelAndView modelAndView=new ModelAndView("pages/alea");
        Optional<Utilisateur>utilisateurOptional=userRepository.findById(id);
        Utilisateur utilisateur= new Utilisateur();
        if (utilisateurOptional.isPresent()){
            utilisateur=utilisateurOptional.get();
        }
        List<pointEtape> pointsParEtape = courreurService.getPointsEquipePourToutesEtapes(utilisateur);
        model.addAttribute("pointsParEtape", pointsParEtape);

        return modelAndView;
    }


}
