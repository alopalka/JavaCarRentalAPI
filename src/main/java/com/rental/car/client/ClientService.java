package com.rental.car.client;

import com.rental.car.client.model.Client;
import com.rental.car.client.model.ClientDto;
import com.rental.car.client.model.command.CreateClientCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

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
    public ClientDto findById(long clientId) {
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

    public void delete(long clientId) {
        clientRepository.deleteById(clientId);
    }
}
