package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.stock.ClientDto;

public interface ClientService {
    ClientDto getClient(Long id);

    ClientDto createClient(ClientDto clientDto, Long shopId);
    ClientDto updateClient(ClientDto clientDto, Long id);

    void removeClient(Long clientId, Long shopId);

    Iterable<ClientDto> getClients(Long shopId);
}
