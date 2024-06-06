package com.example.cinema.controller;

import com.example.cinema.model.*;
import com.example.cinema.repository.CategorieCoureurRepository;
import com.example.cinema.repository.CategorieRepository;
import com.example.cinema.repository.CoureurRepository;
import com.example.cinema.repository.GenreRepository;
import com.example.cinema.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
public class CategorieController {

    private final CoureurRepository coureurRepository;
    private final GenreRepository genreRepository;
    private final CategorieRepository categorieRepository;
    private final CategorieCoureurRepository categorieCoureurRepository;

    public CategorieController(CoureurRepository coureurRepository,
                               GenreRepository genreRepository,
                               CategorieRepository categorieRepository,
                               CategorieCoureurRepository categorieCoureurRepository) {
        this.coureurRepository = coureurRepository;
        this.genreRepository = genreRepository;
        this.categorieRepository = categorieRepository;
        this.categorieCoureurRepository = categorieCoureurRepository;
    }

    @GetMapping("/insertionCategorie")
    public ModelAndView classement(Model model){
        ModelAndView modelAndView=new ModelAndView("pages/GenererCategorie");

        return modelAndView;

    }

    @PostMapping("/insertCategorie")
    public ModelAndView insertcategorie(){
        ModelAndView modelAndView=new ModelAndView("redirect:/home");
        List<Coureur>coureurList=coureurRepository.findAll();
        Genre M=genreRepository.getGenreByGenre("M");
        Genre F=genreRepository.getGenreByGenre("F");
        Categorie junior=categorieRepository.getCategorieByNom("junior");
        Categorie homme=categorieRepository.getCategorieByNom("homme");
        Categorie damme=categorieRepository.getCategorieByNom("damme");

        for (int i = 0; i <coureurList.size() ; i++) {
            if (coureurList.get(i).getGenre().equals(M)){

                CategorieCoureur categorieCoureur=new CategorieCoureur(homme,coureurList.get(i));
                categorieCoureurRepository.save(categorieCoureur);

            } else if (coureurList.get(i).getGenre().equals(F)) {

                CategorieCoureur categorieCoureur=new CategorieCoureur(damme,coureurList.get(i));
                categorieCoureurRepository.save(categorieCoureur);

            }
            int age= Utils.differenceInYears(coureurList.get(i).getDateDeNaissance(),LocalDate.now());
            if (age<18){
                System.out.println("age:"+age);
                CategorieCoureur categorieCoureur=new CategorieCoureur(junior,coureurList.get(i));
                categorieCoureurRepository.save(categorieCoureur);

            }

        }


        return modelAndView;

    }
}
