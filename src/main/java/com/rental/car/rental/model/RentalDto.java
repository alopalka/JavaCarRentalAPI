package com.rental.car.rental.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RentalDto {
    private long id;
    @NotBlank(message = "Car ID can not be blank!")
    private long carId;
    @NotBlank(message = "Client ID can not be blank!")
    private long clientId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dateFrom;

    @Future(message = "Planned rental finish date should be in future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dateTo;

    public static RentalDto fromEntity(Rental rental) {
        return RentalDto.builder()
                .id(rental.getId())
                .carId(rental.getCar().getId())
                .clientId(rental.getClient().getId())
                .dateFrom(rental.getDateFrom())
                .dateTo(rental.getDateTo())
                .build();
    }

    public Rental toEntity() {
        return Rental.builder()
                .dateFrom(dateFrom)
                .dateTo(dateTo)
                .build();
    }
}
