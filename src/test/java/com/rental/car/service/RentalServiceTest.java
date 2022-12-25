package com.rental.car.service;


import com.rental.car.car.CarService;
import com.rental.car.car.model.Car;
import com.rental.car.car.model.CarType;
import com.rental.car.client.model.Client;
import com.rental.car.rental.RentalRepository;
import com.rental.car.rental.RentalService;
import com.rental.car.rental.model.Rental;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RentalServiceTest {

    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalService rentalService;

    @Test
    void shouldReturnAllRentals() {
        Car car1 = Car.builder()
                .id(1)
                .make("BMW")
                .model("F30 M3 CS")
                .carType(CarType.SUPERSPORT)
                .build();

        Car car2 = Car.builder()
                .id(2)
                .make("Audi")
                .model("S4 B8")
                .carType(CarType.SPORT)
                .build();

        Client client1 = Client.builder()
                .id(1)
                .email("freenukes@gmail.com")
                .firstName("Satoshi")
                .lastName("Nakamoto")
                .pesel(2221112221l)
                .build();
        Client client2 = Client.builder()
                .id(2)
                .email("exmple@test.com")
                .firstName("Andrew")
                .lastName("Cobalt")
                .pesel(2231202931l)
                .build();

        Rental rental1 = Rental.builder()
                .id(1)
                .client(client1)
                .car(car1)
                .date(LocalDateTime.now())
                .durationHours(48)
                .price(4000)
                .build();

        Rental rental2 = Rental.builder()
                .id(2)
                .client(client2)
                .car(car2)
                .date(LocalDateTime.now())
                .durationHours(72)
                .price(3500)
                .build();

        List<Rental> rentals = new ArrayList<>(Arrays.asList(rental1, rental2));
        when(rentalRepository.findAll()).thenReturn(rentals);
        List<Rental> rentalsFound = rentalService.findAll();
        assertEquals(rentals, rentalsFound);
    }

    @Test
    void shouldReturnRentalWhenFoundById() {
        long rentalId = 1;
        Car car1 = Car.builder()
                .id(1)
                .make("BMW")
                .model("F30 M3 CS")
                .carType(CarType.SUPERSPORT)
                .build();

        Client client1 = Client.builder()
                .id(1)
                .email("freenukes@gmail.com")
                .firstName("Satoshi")
                .lastName("Nakamoto")
                .pesel(2221112221l)
                .build();

        Rental rental1 = Rental.builder()
                .id(rentalId)
                .client(client1)
                .car(car1)
                .date(LocalDateTime.now())
                .durationHours(48)
                .price(4000)
                .build();
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rental1));
        Rental rentalFound = rentalService.find(rentalId);
        assertEquals(rental1, rentalFound);
    }

    @Test
    void shouldSaveRental() {
        long carId = 1;
        long clientId = 1;
        Car car1 = Car.builder()
                .id(carId)
                .make("BMW")
                .model("F30 M3 CS")
                .carType(CarType.SUPERSPORT)
                .build();

        Client client1 = Client.builder()
                .id(clientId)
                .email("freenukes@gmail.com")
                .firstName("Satoshi")
                .lastName("Nakamoto")
                .pesel(2221112221l)
                .build();

        Rental rental1 = Rental.builder()
                .id(1)
                .client(client1)
                .car(car1)
                .date(LocalDateTime.now())
                .durationHours(48)
                .price(4000)
                .build();
        // problem with carService.findWithLockingById !!!!
        when(rentalRepository.save(rental1)).thenReturn(rental1);
        Rental rentalSaved = rentalService.save(rental1, carId, clientId);
        assertEquals(rental1, rentalSaved);
    }

}
