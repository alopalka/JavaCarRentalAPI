package com.rental.car.car;

import com.rental.car.car.model.Car;
import com.rental.car.car.model.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/car")
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
    public void deleteCar(@PathVariable("id") long carId) {
        carService.delete(carId);
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDto findById(@PathVariable("id") long carId) {
        Car car = carService.find(carId);
        return CarDto.fromEntity(car);
    }
}
