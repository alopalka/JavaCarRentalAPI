package com.rental.car.client;

import com.rental.car.client.model.Client;
import com.rental.car.client.model.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getClients(){
        return clientService.findAll()
                .stream()
                .map(ClientDto::fromEntity)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto saveClient(@RequestBody ClientDto dto){
        Client client = clientService.save(dto.toEntity());
        return ClientDto.fromEntity(client);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@PathVariable("id") long clientId){
        clientService.delete(clientId);
    }
}
