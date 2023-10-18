package com.rental.car.client;

import com.rental.car.client.model.ClientDto;
import com.rental.car.client.model.CreateClientCommand;
import com.rental.car.client.model.UpdateClientCommand;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "CRUD REST API for Client",
        description = "CRUD REST API for Authors to CREATE, READ, UPDATE AND DELETE author details")
public interface ClientController {

    ResponseEntity<Page<ClientDto>> findAll(Pageable pageable);

    ResponseEntity<ClientDto> findById(UUID id);

    ResponseEntity<ClientDto> saveClient(CreateClientCommand command);

    ResponseEntity<ClientDto> updateClient(UpdateClientCommand command);

    ResponseEntity<Void> deleteClient(UUID clientId);
}
