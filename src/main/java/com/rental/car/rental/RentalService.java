package com.rental.car.rental;

import com.rental.car.car.CarService;
import com.rental.car.car.CarUnavalableException;
import com.rental.car.car.model.Car;
import com.rental.car.car.model.CarType;
import com.rental.car.client.ClientService;
import com.rental.car.client.model.Client;
import com.rental.car.rental.model.Rental;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.util.List;

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
        if (rental.getStartDate().isAfter(rental.getEndDate())) {
            throw new RuntimeException("Rental endDate is before startDate !");
        }

        Car car = carService.find(carId);
        Client client = clientService.findById(clientId);

        List<Rental> carsRents = rentalRepository.findAllByCarIdAndDateWithInStartAndEnd(
                carId, rental.getStartDate(), rental.getEndDate());

        if (!carsRents.isEmpty()) {
            throw new CarUnavalableException("Car is being rented at this time!");
        }

        rental.setCar(car);
        rental.setClient(client);

        long hours = Duration.between(rental.getStartDate(), rental.getEndDate()).toHours();
        double priceForRent = car.getCarType().getMultipler() * CarType.BASE * hours;

        rental.setPrice(priceForRent);
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
