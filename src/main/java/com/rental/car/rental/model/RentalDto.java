package com.rental.car.rental.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RentalDto {

    private UUID id;
    @NotBlank(message = "Car ID can not be blank!")
    private UUID carId;
    @NotBlank(message = "Client ID can not be blank!")
    private UUID clientId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime endDate;

    @ReadOnlyProperty
    private double price;

    public static RentalDto fromEntity(Rental rental) {
        return RentalDto.builder()
                .id(rental.getId())
                .carId(rental.getCar().getId())
                .clientId(rental.getClient().getId())
                .startDate(rental.getStartDate())
                .endDate(rental.getEndDate())
                .price(rental.getPrice())
                .build();
    }

    public Rental toEntity() {
        return Rental.builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
