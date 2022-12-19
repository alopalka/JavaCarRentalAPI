package com.rental.car.client.model;

import com.rental.car.rental.model.Rental;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private long pesel;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "client")
    private Set<Rental> rentals;
}
