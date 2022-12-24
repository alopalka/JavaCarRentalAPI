package com.rental.car.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientDto {

    private long id;
    @NotBlank(message = "First name can not be blank!")
    @Pattern(regexp = "[A-Z][a-z]+", message = "First name in wrong format!")
    private String firstName;
    @NotBlank(message = "Last name can not be blank!")
    @Pattern(regexp = "[A-Z][a-z]+", message = "Last name in wrong format!")
    private String lastName;
    @NotBlank(message = "Pesel can not be blank!")
    private long pesel;
    @NotBlank(message = "Email can not be blank!")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Email in wrong format!")
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

    public Client toEntity() {
        return Client.builder()
                .firstName(firstName)
                .lastName(lastName)
                .pesel(pesel)
                .email(email)
                .build();
    }
}
