package com.rental.car.rental;

import com.rental.car.rental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
//    List<Rental> findAllByCarIdAndDateBetween(long id, LocalDateTime timeFrom, LocalDateTime timeTo);

    @Query(value = "SELECT r FROM Rental r WHERE r.startDate > ?1 and r.endDate > ?2")
    List<Rental> findAllByCarIdAndDateWithInStartAndEnd(long id, LocalDateTime startDate, LocalDateTime endDate);
}

