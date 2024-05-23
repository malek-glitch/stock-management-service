package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.stock.ClientDto;
import com.crocostaud.stockmanagement.model.stock.Client;

public interface ClientService {
    Client getClient(Long id);
    ClientDto createClient(ClientDto clientDto);
    ClientDto updateClient(ClientDto clientDto, Long id);
    void removeClient(Long clientId);


}
