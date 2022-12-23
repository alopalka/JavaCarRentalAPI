package com.rental.car.rental;

import com.rental.car.rental.model.Rental;
import com.rental.car.rental.model.RentalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        long carId = dto.getCarId();
        long clientId = dto.getClientId();
        Rental rental = rentalService.save(dto.toEntity(), carId, clientId);
        return RentalDto.fromEntity(rental);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") long clientId){
        rentalService.delete(clientId);
    }

}
