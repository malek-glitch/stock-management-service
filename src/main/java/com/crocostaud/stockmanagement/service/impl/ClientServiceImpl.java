package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.ClientDto;
import com.crocostaud.stockmanagement.model.Client;
import com.crocostaud.stockmanagement.repository.ClientRepository;
import com.crocostaud.stockmanagement.service.ClientService;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepo;

    public ClientServiceImpl(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public Client getClient(Long id) {
        return clientRepo.getReferenceById(id);
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Client client = Client.builder()
                .phone(clientDto.getPhone())
                .name(clientDto.getName())
                .build();

        Client savedClient = clientRepo.save(client);

        return mapToDto(savedClient);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto, Long id) {
        Client client = clientRepo.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));

        clientRepo.updateNameAndPhoneById(clientDto.getName(), clientDto.getPhone(), id);

        Client updatedClient = clientRepo.getReferenceById(id);
        return mapToDto(updatedClient);
    }

    @Override
    public void removeClient(Long clientId) {
        clientRepo.deleteById(clientId);
    }

    private ClientDto mapToDto(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .phone(client.getPhone())
                .build();
    }
}
