package com.rental.car.car.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {

    private long id;
    private String make;
    private String model;

    public static CarDto fromEntity(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .make(car.getMake())
                .model(car.getModel())
                .build();
    }

    public Car toEntity() {
        return Car.builder()
                .make(make)
                .model(model)
                .build();
    }

}
