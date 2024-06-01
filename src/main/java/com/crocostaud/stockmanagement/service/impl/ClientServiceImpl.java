package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.stock.ClientDto;
import com.crocostaud.stockmanagement.model.stock.Client;
import com.crocostaud.stockmanagement.model.stock.Shop;
import com.crocostaud.stockmanagement.repository.ClientRepository;
import com.crocostaud.stockmanagement.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepo;

    public ClientServiceImpl(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public ClientDto getClient(Long id) {
        return mapToDto(clientRepo.findById(id).orElse(null));
    }

    @Override
    public ClientDto createClient(ClientDto clientDto, Long shopId) {
        Client client = Client.builder()
                .email(clientDto.getEmail())
                .phone(clientDto.getPhone())
                .name(clientDto.getName())
                .shop(new Shop(shopId))
                .build();
        Client savedClient = clientRepo.save(client);
        return mapToDto(savedClient);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto, Long id) {
        Client client = clientRepo.findById(id).orElse(null);
        if (client == null)
            return null;

        clientRepo.updateNameAndPhoneById(clientDto.getName(), clientDto.getPhone(), id);

        Client updatedClient = clientRepo.findById(id).orElse(null);
        return mapToDto(updatedClient);
    }

    @Override
    public void removeClient(Long clientId, Long shopId) {
        clientRepo.deleteByIdAndShop_id(clientId, new Shop(shopId));
    }

    @Override
    public Iterable<ClientDto> getClients(Long shopId) {
        return clientRepo.findAllByShopId(shopId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    private ClientDto mapToDto(Client client) {
        if (client == null)
            return null;
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .phone(client.getPhone())
                .email(client.getEmail())
                .shopId(client.getShop().getId())
                .build();
    }
}
