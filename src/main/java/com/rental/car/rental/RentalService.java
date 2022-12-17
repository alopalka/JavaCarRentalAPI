package com.rental.car.rental;

import com.rental.car.rental.model.Rental;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;

    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    public Rental save(Rental rental) {
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
