package com.rental.car.service;

import com.rental.car.client.ClientRepository;
import com.rental.car.client.ClientService;
import com.rental.car.client.model.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void shouldReturnEntityWhenFoundById() {
        long clientId = 1;
        Client client = Client.builder()
                .id(clientId)
                .email("freenukes@gmail.com")
                .firstName("Satoshi")
                .lastName("Nakamoto")
                .pesel(2221112221l)
                .build();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        Client clientFound = clientService.find(clientId);
        assertEquals(client, clientFound);
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenClientNotFound() {
        long clientId = 1;
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> clientService.find(clientId));
        assertEquals("Client with provided id does not exist!", exception.getMessage());
    }

    @Test
    void shouldSaveEntity(){
        Client client = Client.builder()
                .id(1)
                .email("freenukes@gmail.com")
                .firstName("Satoshi")
                .lastName("Nakamoto")
                .pesel(2221112221l)
                .build();
        when(clientRepository.save(client)).thenReturn(client);
        Client clientSaved = clientService.save(client);
        assertEquals(client.getEmail(),clientSaved.getEmail());
        assertEquals(client.getPesel(),clientSaved.getPesel());
        assertEquals(client.getLastName(),clientSaved.getLastName());
    }

}
