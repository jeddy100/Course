package com.example.cinema.security;

import com.example.cinema.model.Utilisateur;
import com.example.cinema.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector,
                                           UserRepository userRepository) throws Exception {
                ////

        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(mvcMatcherBuilder.pattern("/login")).permitAll()
                .requestMatchers(mvcMatcherBuilder.pattern("/insertimport")).authenticated()
                .requestMatchers(mvcMatcherBuilder.pattern("/classementGeneral")).authenticated()
                .requestMatchers(mvcMatcherBuilder.pattern("/home")).authenticated()
                .requestMatchers(mvcMatcherBuilder.pattern("/listeEtapesClassement")).authenticated()
                .requestMatchers(mvcMatcherBuilder.pattern("/classementEtape")).authenticated()
                .requestMatchers(mvcMatcherBuilder.pattern("/homeclient")).hasRole("client")
                .requestMatchers(mvcMatcherBuilder.pattern("/payement/")).hasRole("client")
                .requestMatchers(mvcMatcherBuilder.pattern("/ajouterCoureur/")).hasRole("client")
                .requestMatchers(mvcMatcherBuilder.pattern("/listeEtapes/")).hasRole("client")
                .requestMatchers(mvcMatcherBuilder.pattern("/listeEtapesAdmin/")).hasRole("admin")
                .requestMatchers(mvcMatcherBuilder.pattern("/listeEtapesAdmin/")).hasRole("certificat")
                .requestMatchers(mvcMatcherBuilder.pattern("/importdonneespost/")).hasRole("admin")
                .requestMatchers(mvcMatcherBuilder.pattern("/insertionCategorie/")).hasRole("admin")
                .requestMatchers(mvcMatcherBuilder.pattern("/insertCategorie/")).hasRole("admin")
                .requestMatchers(mvcMatcherBuilder.pattern("/importdonneesresultatpost/")).hasRole("admin")
                .requestMatchers(mvcMatcherBuilder.pattern("/ListePenalite/")).hasRole("admin")
                .requestMatchers(mvcMatcherBuilder.pattern("/penalitePost/")).hasRole("admin")
                .requestMatchers(mvcMatcherBuilder.pattern("/insertPenalite/")).hasRole("admin")
                .requestMatchers(mvcMatcherBuilder.pattern("/listeCoureurEtape/ajouterHeureArrivee/")).hasRole("admin")
                .requestMatchers(mvcMatcherBuilder.pattern("/WEB-INF/**")).permitAll()
                .requestMatchers(mvcMatcherBuilder.pattern("/public/**")).permitAll()
                .requestMatchers(mvcMatcherBuilder.pattern("/inscription")).permitAll()
                .requestMatchers(mvcMatcherBuilder.pattern("/inscrire")).permitAll()
                .requestMatchers(mvcMatcherBuilder.pattern("/loginclient")).permitAll()
                .requestMatchers(mvcMatcherBuilder.pattern("/ldev")).hasRole("client")
                .requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
                .anyRequest().authenticated()
        );

                ////
//                .authorizeHttpRequests(
//                        auth -> {
//                            //c est ici qu on authorize
//                            auth.requestMatchers(HttpMethod.GET,"/login").permitAll();
//                            auth.requestMatchers(HttpMethod.GET,"/insertimport").authenticated();
//                            auth.requestMatchers("/WEB-INF/**","/public/**");
//                            auth.anyRequest().authenticated();
//
//                        })
        return http
                .formLogin(
                        form ->{
                            //c'est la fonction ogin dans controller
                            form.loginPage("/login");
                            ///gerer les exceptions
                            form.failureHandler(((request, response, exception) -> {
                                ///

                                ///
                                System.out.println("reponse:"+response+"exception:"+exception);
                                response.sendRedirect("/login?error");
                            }));



                            ///on fait par rapport aux input dans la jsp

                            form.usernameParameter("nom");
                            form.passwordParameter("mdp");

                            //quand connecte ca va rediriger ici

                            form.defaultSuccessUrl("/home");

                        })


                .logout(
                        logout ->{
                            logout.logoutUrl("/logout");
                            logout.logoutSuccessUrl("/");
                            logout.permitAll();
                        }
                )
                .build();


    }
    //encryptageeee
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(getUserDetailsService())
                .passwordEncoder(bCryptPasswordEncoder);

//        authenticationManagerBuilder.authenticationProvider(phoneNumberAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }


}
