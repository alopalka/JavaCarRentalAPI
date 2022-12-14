package com.rental.car.rental.model;

import com.rental.car.car.model.Car;
import com.rental.car.client.model.Client;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Car car;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;
}
