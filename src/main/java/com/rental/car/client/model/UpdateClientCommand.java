package com.rental.car.client.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Setter
public class UpdateClientCommand {

    @NotNull
    private UUID id;

    @NotBlank(message = "FIRSTNAME_NOT_BLANK")
    @Pattern(regexp = "[A-Z][a-z]+", message = "FIRSTNAME_WRONG_FORMAT")
    private String firstName;

    @NotBlank(message = "LASTNAME_NOT_BLANK")
    @Pattern(regexp = "[A-Z][a-z]+", message = "LASTNAME_WRONG_FORMAT")
    private String lastName;

    @NotBlank(message = "PESEL_NOT_BLANK")
    private String pesel;

    @NotBlank(message = "EMAIL_NOT_BLANK")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "EMAIL_WRONG_FORMAT")
    private String email;
}
