package com.rental.car.client.mappings;

import com.rental.car.client.model.Client;
import com.rental.car.client.model.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientToClientDtoMapper {

    public ClientDto fromEntity(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .pesel(client.getPesel())
                .email(client.getEmail())
                .build();
    }
}
