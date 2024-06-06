package com.example.cinema.service;

import com.example.cinema.model.Utilisateur;
import com.example.cinema.repository.UserRepository;
import com.example.cinema.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUsersDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUsersDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    ///c est ici qu on cherche dans la base
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Utilisateur utilisateur =userRepository.getUserByNom(username);
//        if (utilisateur ==null){
//            throw new UsernameNotFoundException("utilisateur pas disponible");
//        }
//
//        return new CustomUserDetails(utilisateur);
//    }

    @Override
    ///c est ici qu on cherche dans la base
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur userModel= userRepository.getUserByNom(username);
        System.out.println("user details : "+userModel);
        if(userModel==null){
            userModel=userRepository.getUserByNom(username);
            if(userModel==null){
                throw  new UsernameNotFoundException("impossible de trouver l'utilisateur");
            }
        }
        return new CustomUserDetails(userModel);
    }

    public static Utilisateur getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getUtilisateur();
        }

        return null;
    }

}
