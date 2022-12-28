package com.rental.car.car;

import com.rental.car.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
}
