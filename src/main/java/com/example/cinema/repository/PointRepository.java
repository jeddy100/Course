package com.example.cinema.repository;

import com.example.cinema.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
    Point findByClassement(Long classement);

}