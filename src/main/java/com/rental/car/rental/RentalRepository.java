package com.rental.car.rental;

import com.rental.car.rental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findAllCarIdAndDateBetween(long id, LocalDateTime fromDate, LocalDateTime toDate);
}
