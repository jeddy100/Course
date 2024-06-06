package com.example.cinema.service;

import com.example.cinema.model.*;
import com.example.cinema.object.Ranking;
import com.example.cinema.object.pointEtape;
import com.example.cinema.repository.CoureurRepository;
import com.example.cinema.repository.EtapeRepository;
import com.example.cinema.repository.PointRepository;
import com.example.cinema.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourreurService {
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private CoureurRepository coureurRepository;
    @Autowired
    private TempsCoureurEtapeRepository tempsCoureurEtapeRepository;
    @Autowired
    private EtapeRepository etapeRepository;

    public double getNbPointsByClassement(Long classement) {
        Point point = pointRepository.findByClassement(classement);
        return (point != null) ? point.getNbPoint() : 0;
    }

    public  List<Ranking> getClassementEtape(Long etapeId){
        List<Ranking> rankingList=new ArrayList<>();
        List<Object[]> classement = coureurRepository.findClassementByEtapeId2(etapeId);
        for (int i = 0; i < classement.size() ; i++) {
            Optional<TempsCoureurEtape> optional=tempsCoureurEtapeRepository.findById((Long) classement.get(i)[0]);
            TempsCoureurEtape tempsCoureurEtape=new TempsCoureurEtape();
            if(optional.isPresent()){
                tempsCoureurEtape=optional.get();
            }
            BigDecimal tempstotal= (BigDecimal) classement.get(i)[1];
            Long rang= (Long) classement.get(i)[2];
            Duration duration= Utils.parseDuration(String.valueOf(classement.get(i)[3]));
            double point= getNbPointsByClassement(rang);
//            System.out.println("rang:"+tempsCoureurEtape.getCoureurEtape().getCoureur().getNom()+" "+point);
            Ranking ranking=new Ranking(tempsCoureurEtape,tempstotal,rang, duration, point);
            rankingList.add(ranking);

        }


        return rankingList;
    }

    public double getPointequipeByEtape(Utilisateur utilisateur){
        double rep=0;
        List<Etape>etapeList=etapeRepository.findAll();
        for (int i = 0; i < etapeList.size(); i++) {
            List<Ranking>rankingList=getClassementEtape(etapeList.get(i).getId_etape());
            for (int j = 0; j <rankingList.size() ; j++) {
                if(rankingList.get(j).getTempsCoureurEtape().getCoureurEtape().getCoureur().getUtilisateur().equals(utilisateur)){
                    rep=rep+rankingList.get(j).getPoint();
                }
            }
        }
        return rep;
    }

    public double getPointequipepourEtape(Utilisateur utilisateur,Etape etape){
        double rep=0;

            List<Ranking>rankingList=getClassementEtape(etape.getId_etape());
            for (int j = 0; j <rankingList.size() ; j++) {
                if(rankingList.get(j).getTempsCoureurEtape().getCoureurEtape().getCoureur().getUtilisateur().equals(utilisateur)){
                    rep=rep+rankingList.get(j).getPoint();
                }

        }
        return rep;
    }

    public List<pointEtape> getPointsEquipePourToutesEtapes(Utilisateur utilisateur) {
        List<pointEtape> pointsParEtape = new ArrayList<>();

        // Parcours de toutes les étapes
        List<Etape> toutesLesEtapes = etapeRepository.findAll();// Méthode à implémenter pour obtenir toutes les étapes
        for (Etape etape : toutesLesEtapes) {
            double pointsPourEtape = getPointequipepourEtape(utilisateur, etape);
            pointsParEtape.add(new pointEtape(etape.getNom(), pointsPourEtape));
        }

        return pointsParEtape;
    }




    /////////////////par categorie

    public  List<Ranking> getClassementEtapeCategorie(Long etapeId ,Long categorieId){
        List<Ranking> rankingList=new ArrayList<>();
        List<Object[]> classement = coureurRepository.findClassementByEtapeIdAndCategorie(etapeId,categorieId);
        for (int i = 0; i < classement.size() ; i++) {
            Optional<TempsCoureurEtape> optional=tempsCoureurEtapeRepository.findById((Long) classement.get(i)[0]);
            TempsCoureurEtape tempsCoureurEtape=new TempsCoureurEtape();
            if(optional.isPresent()){
                tempsCoureurEtape=optional.get();
            }
            BigDecimal tempstotal= (BigDecimal) classement.get(i)[1];
            Long rang= (Long) classement.get(i)[2];
            double point= getNbPointsByClassement(rang);
//            System.out.println("rang:"+tempsCoureurEtape.getCoureurEtape().getCoureur().getNom()+" "+point);
            Ranking ranking=new Ranking(tempsCoureurEtape,tempstotal,rang,point);
            rankingList.add(ranking);

        }


        return rankingList;
    }

    public double getPointequipeByEtapeCategorie(Utilisateur utilisateur,Long categorieId){
        double rep=0;
        List<Etape>etapeList=etapeRepository.findAll();
        for (int i = 0; i < etapeList.size(); i++) {
            List<Ranking>rankingList=getClassementEtapeCategorie(etapeList.get(i).getId_etape(),categorieId);
            for (int j = 0; j <rankingList.size() ; j++) {
                if(rankingList.get(j).getTempsCoureurEtape().getCoureurEtape().getCoureur().getUtilisateur().equals(utilisateur)){
                    rep=rep+rankingList.get(j).getPoint();
                }
            }
        }
        return rep;
    }
}
