package com.rental.car.client.model;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ClientDto(
        UUID id,
        String firstName,
        String lastName,
        String pesel,
        String email
) {
}
