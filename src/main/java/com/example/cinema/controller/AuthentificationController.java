package com.example.cinema.controller;

import com.example.cinema.model.Utilisateur;
import com.example.cinema.repository.UserRepository;
import com.example.cinema.service.CustomUsersDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class AuthentificationController {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthentificationController(BCryptPasswordEncoder passwordEncoder,
                                      UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public ModelAndView home(){
        ModelAndView modelAndView=new ModelAndView("pages/auth-login");

        return modelAndView;

    }

    @GetMapping("/inscription")
    public ModelAndView inscription(){
        ModelAndView modelAndView=new ModelAndView("pages/inscription");

        return modelAndView;

    }
    @PostMapping("/inscrire")
    public ModelAndView inscrire(@RequestParam HashMap<String,Object> obj){
        ModelAndView modelAndView=new ModelAndView("pages/inscription");
        String nom= String.valueOf(obj.get("nom"));
        String role= String.valueOf(obj.get("role"));
        String mdp= passwordEncoder.encode( String.valueOf(obj.get("mdp")));
        Utilisateur utilisateur=new Utilisateur(nom,mdp,role);
        userRepository.save(utilisateur);


        return modelAndView;

    }



    @GetMapping("/home")
    public ModelAndView ho(Model model){
        ModelAndView modelAndView=new ModelAndView("pages/home1");
        Utilisateur utilisateur= CustomUsersDetailsService.getAuthenticatedUser();
        model.addAttribute("utilisateur",utilisateur);
        return modelAndView;

    }


    @GetMapping("/")
    public ModelAndView accueil(){
        ModelAndView modelAndView=new ModelAndView("pages/index");

        return modelAndView;

    }

    @GetMapping("/loginclient")
    public ModelAndView client(){
        ModelAndView modelAndView=new ModelAndView("pages/loginclient");

        return modelAndView;

    }

//    @GetMapping("/error?continue")
//    public String handleError() {
//        // Vous pouvez ajouter une logique pour afficher des messages d'erreur personnalisés
//        return  "redirect:/home";
//    }




}
