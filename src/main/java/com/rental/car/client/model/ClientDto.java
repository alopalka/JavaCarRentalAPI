package com.rental.car.client.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Builder
@Getter
@Setter
@Schema(description = "Data Transfer Object representing a client.")
public class ClientDto extends RepresentationModel<ClientDto> {

    @Schema(description = "Unique identifier of the client.", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;

    @Schema(description = "First name of the client.", example = "Jan")
    private String firstName;

    @Schema(description = "Last name of the client.", example = "Kowalski")
    private String lastName;

    @Schema(description = "Personal Identification Number of the client (Polish: PESEL).", example = "90090515836")
    private String pesel;

    @Schema(description = "Email address of the client.", example = "jan.kowalski@example.com")
    private String email;

    public static ClientDto fromEntity(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .pesel(client.getPesel())
                .email(client.getEmail())
                .build();
    }
}