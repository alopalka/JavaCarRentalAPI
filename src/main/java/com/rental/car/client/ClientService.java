package com.rental.car.client;

import com.rental.car.client.model.Client;
import com.rental.car.client.model.ClientDto;
import com.rental.car.client.model.CreateClientCommand;
import com.rental.car.client.model.UpdateClientCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(ClientDto::fromEntity);
    }

    @Transactional(readOnly = true)
    public ClientDto findById(UUID clientId) {
        return clientRepository.findById(clientId)
                .map(ClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Client with provided id does not exist!"));
    }

    public ClientDto save(CreateClientCommand command) {
        Client client = clientRepository.save(Client.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .pesel(command.getPesel())
                .email(command.getEmail())
                .build());

        return ClientDto.fromEntity(client);
    }

    @Transactional
    public ClientDto update(UpdateClientCommand command) {
        Client clientToUpdate = clientRepository.findById(command.getId())
                .orElseThrow(EntityNotFoundException::new);

        if (clientToUpdate.getVersion() != command.getVersion()) {
            throw new OptimisticLockException("Version mismatch: current version is " + clientToUpdate.getVersion() + " but command has version " + command.getVersion());
        }

        clientToUpdate.setFirstName(command.getFirstName());
        clientToUpdate.setLastName(command.getLastName());
        clientToUpdate.setPesel(command.getPesel());
        clientToUpdate.setEmail(command.getEmail());

        return ClientDto.fromEntity(clientRepository.saveAndFlush(clientToUpdate));
    }

    public void delete(UUID clientId) {
        clientRepository.deleteById(clientId);
    }
}
