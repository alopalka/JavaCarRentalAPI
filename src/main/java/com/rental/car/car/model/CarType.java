package com.rental.car.car.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public enum CarType {
    ECONOMY(1),
    SPORT(2),
    SUPERSPORT(4),
    PREMIUM(3),
    LUXURY(3.5),
    ;
    private final double multipler;
    public static final double BASE = 16;
}
