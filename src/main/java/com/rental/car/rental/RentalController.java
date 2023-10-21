package com.rental.car.rental;

import com.rental.car.rental.model.Rental;
import com.rental.car.rental.model.RentalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/rentals")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RentalDto> getRentals() {
        return rentalService.findAll()
                .stream()
                .map(RentalDto::fromEntity)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RentalDto save(@RequestBody RentalDto dto) {
        UUID carId = dto.getCarId();
        UUID clientId = dto.getClientId();
        Rental rental = rentalService.save(dto.toEntity(), carId, clientId);
        return RentalDto.fromEntity(rental);
    }

    @GetMapping(value = "/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentalDto find(@PathVariable("id") UUID rentalId) {
        Rental rental = rentalService.find(rentalId);
        return RentalDto.fromEntity(rental);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID rentalId) {
        rentalService.delete(rentalId);
    }

}
