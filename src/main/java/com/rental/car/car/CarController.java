package com.rental.car.car;

import com.rental.car.car.model.Car;
import com.rental.car.car.model.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> getCars() {
        return carService.findAll().stream()
                .map(CarDto::fromEntity)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto createCar(@RequestBody CarDto dto) {
        Car car = carService.save(dto.toEntity());
        return CarDto.fromEntity(car);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCar(@PathVariable("id") UUID carId) {
        carService.delete(carId);
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDto findById(@PathVariable("id") UUID carId) {
        Car car = carService.find(carId);
        return CarDto.fromEntity(car);
    }
}
