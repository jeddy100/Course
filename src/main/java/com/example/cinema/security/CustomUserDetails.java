package com.example.cinema.security;

import com.example.cinema.model.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    public final Utilisateur utilisateur;

    public CustomUserDetails(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

//

    ////modif debut
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Renvoie une liste d'autorités basée sur les rôles de l'utilisateur
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole()));
    }
    ///fin

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    @Override
    public String getPassword() {
        return utilisateur.getPassword();
    }

    @Override
    public String getUsername() {
        return utilisateur.getNom();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
