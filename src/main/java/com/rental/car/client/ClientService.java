package com.rental.car.client;

import com.rental.car.client.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public void delete(long clientId) {
        clientRepository.deleteById(clientId);
    }

    public Client find(long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Rental with provided id does not exist!"));
    }

    public Client findWithLockingById(long clientId) {
        return clientRepository.findWithLockingById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Rental with provided id does not exist!"));
    }
}
