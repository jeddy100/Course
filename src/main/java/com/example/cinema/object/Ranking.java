package com.example.cinema.object;

import com.example.cinema.model.TempsCoureurEtape;

import java.math.BigDecimal;
import java.time.Duration;

public class Ranking {
    Long id_ranking;
    TempsCoureurEtape tempsCoureurEtape;
    BigDecimal tempstotal;
    Long rang;
    Duration duration;
    double point;

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public Long getId_ranking() {
        return id_ranking;
    }

    public void setId_ranking(Long id_ranking) {
        this.id_ranking = id_ranking;
    }

    public TempsCoureurEtape getTempsCoureurEtape() {
        return tempsCoureurEtape;
    }

    public void setTempsCoureurEtape(TempsCoureurEtape tempsCoureurEtape) {
        this.tempsCoureurEtape = tempsCoureurEtape;
    }

    public BigDecimal getTempstotal() {
        return tempstotal;
    }

    public void setTempstotal(BigDecimal tempstotal) {
        this.tempstotal = tempstotal;
    }

    public Long getRang() {
        return rang;
    }

    public void setRang(Long rang) {
        this.rang = rang;
    }

    public Ranking(Long id_ranking, TempsCoureurEtape tempsCoureurEtape, BigDecimal tempstotal, Long rang) {
        this.id_ranking = id_ranking;
        this.tempsCoureurEtape = tempsCoureurEtape;
        this.tempstotal = tempstotal;
        this.rang = rang;
    }

    public Ranking(TempsCoureurEtape tempsCoureurEtape, BigDecimal tempstotal, Long rang) {
        this.tempsCoureurEtape = tempsCoureurEtape;
        this.tempstotal = tempstotal;
        this.rang = rang;
    }

    public Ranking() {
    }

    public Ranking(Long id_ranking, TempsCoureurEtape tempsCoureurEtape, BigDecimal tempstotal, Long rang, double point) {
        this.id_ranking = id_ranking;
        this.tempsCoureurEtape = tempsCoureurEtape;
        this.tempstotal = tempstotal;
        this.rang = rang;
        this.point = point;
    }

    public Ranking(TempsCoureurEtape tempsCoureurEtape, BigDecimal tempstotal, Long rang, double point) {
        this.tempsCoureurEtape = tempsCoureurEtape;
        this.tempstotal = tempstotal;
        this.rang = rang;
        this.point = point;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Ranking(Long id_ranking, TempsCoureurEtape tempsCoureurEtape, BigDecimal tempstotal, Long rang, Duration duration, double point) {
        this.id_ranking = id_ranking;
        this.tempsCoureurEtape = tempsCoureurEtape;
        this.tempstotal = tempstotal;
        this.rang = rang;
        this.duration = duration;
        this.point = point;
    }

    public Ranking(TempsCoureurEtape tempsCoureurEtape, BigDecimal tempstotal, Long rang, Duration duration, double point) {
        this.tempsCoureurEtape = tempsCoureurEtape;
        this.tempstotal = tempstotal;
        this.rang = rang;
        this.duration = duration;
        this.point = point;
    }
}
