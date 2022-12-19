package com.rental.car.car.model;

import com.rental.car.rental.model.Rental;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String make;
    private String model;

    @OneToMany(mappedBy = "car")
    private Set<Rental> rentals;
}

