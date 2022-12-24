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

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void shouldThrowEntityNotFoundExceptionWhenCarNotFound() {
        long carId = 1;
        when(carRepository.findById(carId)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> carService.find(carId));
        assertEquals("Car with provided id does not exist!", exception.getMessage());
    }


    @Test
    void shouldSaveEntity() {
        Car car = Car.builder()
                .id(1)
                .make("Audi")
                .model("RS6 C7 Performance")
                .carType(CarType.SUPERSPORT)
                .build();
        when(carRepository.save(car)).thenReturn(car);
        Car savedCar = carService.save(car);
        assertEquals(car.getMake(), savedCar.getMake());
        assertEquals(car.getModel(), savedCar.getModel());
        assertEquals(car.getCarType(), savedCar.getCarType());
    }
}
