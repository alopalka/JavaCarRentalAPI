package com.rental.car.service;

import com.rental.car.car.CarRepository;
import com.rental.car.car.CarService;
import com.rental.car.car.model.Car;
import com.rental.car.car.model.CarType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    void shouldReturnEntityWhenCarFoundById() {
        long carId = 1;
        Car carFromRepo = Car.builder()
                .id(1)
                .make("BMW")
                .model("F30 M3 CS")
                .carType(CarType.SUPERSPORT)
                .build();
        when(carRepository.findById(carId)).thenReturn(Optional.of(carFromRepo));
        Car carFound = carService.find(carId);
        assertEquals(carFromRepo, carFound);
    }
}
