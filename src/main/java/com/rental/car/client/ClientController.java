package com.rental.car.client;

import com.rental.car.client.model.ClientDto;
import com.rental.car.client.model.CreateClientCommand;
import com.rental.car.client.model.UpdateClientCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "Client Management", description = "APIs that handle operations related to managing clients including creating, reading, updating, and deleting client details.")
public interface ClientController {

    @Operation(summary = "Retrieve a paginated list of clients",
            description = "Provides a list of clients with pagination capabilities.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved paginated list of clients")
    ResponseEntity<Page<ClientDto>> findAll(Pageable pageable);

    @Operation(summary = "Retrieve a client by its ID",
            description = "Fetch a specific client's details using its unique ID.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved client details")
    ResponseEntity<ClientDto> findById(
            @Parameter(description = "Unique ID of the client.") UUID id
    );

    @Operation(summary = "Create a new client",
            description = "Add a new client to the system and return its details.")
    @ApiResponse(responseCode = "201", description = "Successfully created a new client")
    ResponseEntity<ClientDto> saveClient(
            @Parameter(description = "Client details to be added.") CreateClientCommand command
    );

    @Operation(summary = "Update an existing client",
            description = "Modify an existing client's details using its unique ID.")
    @ApiResponse(responseCode = "200", description = "Successfully updated the client details")
    ResponseEntity<ClientDto> updateClient(
            @Parameter(description = "Updated client details.") UpdateClientCommand command
    );

    @Operation(summary = "Delete a client by its ID",
            description = "Remove a client from the system using its unique ID.")
    @ApiResponse(responseCode = "204", description = "Successfully deleted the client")
    ResponseEntity<Void> deleteClient(
            @Parameter(description = "Unique ID of the client to be deleted.") UUID clientId
    );
}
