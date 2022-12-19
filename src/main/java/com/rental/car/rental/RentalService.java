package com.rental.car.rental;

import com.rental.car.car.CarService;
import com.rental.car.car.model.Car;
import com.rental.car.client.ClientService;
import com.rental.car.client.model.Client;
import com.rental.car.rental.model.Rental;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final CarService carService;
    private final ClientService clientService;

    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    public Rental save(Rental rental, long carId, long clientId) {
        Car car = carService.findWithLockingById(carId);
        Client client = clientService.findWithLockingById(clientId);

        List<Rental> carsRents = rentalRepository.findAllByCarIdAndDateBetween(
                carId, rental.getDate(), rental.getDate().plusHours(rental.getDurationHours())
        );

        if (!carsRents.isEmpty()) {
            throw new EntityNotFoundException("Car is being rented at this time!");
        }
        rental.setCar(car);
        rental.setClient(client);
        return rentalRepository.save(rental);
    }

    public void delete(long rentalId) {
        rentalRepository.deleteById(rentalId);
    }

    public Rental find(long rentalId) {
        return rentalRepository.findById(rentalId)
                .orElseThrow(() -> new EntityNotFoundException("Rental with provided id does not exist!"));
    }

}
