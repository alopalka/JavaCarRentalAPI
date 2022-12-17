package com.rental.car.rental.model;

import com.rental.car.car.model.Car;
import com.rental.car.client.model.Client;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Client client;
    private Car car;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
}
