package com.rental.car.rental;

import com.rental.car.rental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface RentalRepository extends JpaRepository<Rental, Long> {
    @Query(value = "SELECT r FROM Rental r WHERE r.car.id = :id AND (r.startDate BETWEEN :startDate AND :endDate OR r.endDate BETWEEN :startDate AND :endDate)")
    List<Rental> findAllByCarIdAndDateWithInStartAndEnd(@Param("id") long id, @Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate);
}

