package com.example.cinema.repository;

import com.example.cinema.imp.DonneesPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DonneesPointRepository extends JpaRepository<DonneesPoint, Long> {
    @Modifying
    @Query(value = " insert into point(classement, nb_point) select cast(classement as bigint),cast(point as double precision)from donnees_point;",nativeQuery = true)
    void insertPointsFromSelect();
}