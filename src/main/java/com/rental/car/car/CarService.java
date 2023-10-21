package com.rental.car.car;

import com.rental.car.car.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public void delete(UUID carId) {
        carRepository.deleteById(carId);
    }

    public Car find(UUID carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Car with provided id does not exist!"));
    }
}