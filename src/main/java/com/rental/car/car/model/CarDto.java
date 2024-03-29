package com.rental.car.car.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {

    private UUID id;
    private String make;
    private String model;
    private CarType carType;

    public static CarDto fromEntity(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .make(car.getMake())
                .model(car.getModel())
                .carType(car.getCarType())
                .build();
    }

    public Car toEntity() {
        return Car.builder()
                .make(make)
                .model(model)
                .carType(carType)
                .build();
    }

}
