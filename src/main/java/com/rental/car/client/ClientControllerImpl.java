package com.rental.car.client;

import com.rental.car.client.model.ClientDto;
import com.rental.car.client.model.CreateClientCommand;
import com.rental.car.client.model.UpdateClientCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/clients")
@RequiredArgsConstructor
public class ClientControllerImpl implements ClientController {
    private final ClientService clientService;

    @GetMapping
    @Override
    public ResponseEntity<Page<ClientDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(clientService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ClientDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public ResponseEntity<ClientDto> saveClient(@RequestBody @Valid CreateClientCommand command) {
        return ResponseEntity.ok(clientService.save(command));
    }

    @PutMapping
    @Override
    public ResponseEntity<ClientDto> updateClient(@RequestBody @Valid UpdateClientCommand command) {
        return ResponseEntity.ok(clientService.update(command));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<Void> deleteClient(@PathVariable("id") UUID id) {
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
