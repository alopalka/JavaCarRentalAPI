package com.rental.car.car.model;

public enum CarType {
    ECONOMY(1),
    SPORT(2),
    SUPERSPORT(4),
    PREMIUM(3),
    LUXURY(3.5),
    ;
    private double multipler;

    CarType(double multipler) {
        this.multipler = multipler;
    }

    public double getMultipler() {
        return multipler;
    }

    public void setMultipler(double multipler) {
        this.multipler = multipler;
    }
}
