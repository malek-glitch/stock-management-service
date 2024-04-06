package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.ClientDto;
import com.crocostaud.stockmanagement.model.Client;

public interface ClientService {
    Client getClient(Long id);
    ClientDto createClient(ClientDto clientDto);
    ClientDto updateClient(ClientDto clientDto, Long id);
    void removeClient(Long clientId);


}
