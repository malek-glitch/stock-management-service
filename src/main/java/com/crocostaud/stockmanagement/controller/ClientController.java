package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.stock.ClientDto;
import com.crocostaud.stockmanagement.model.stock.ShopUser;
import com.crocostaud.stockmanagement.service.ClientService;
import com.crocostaud.stockmanagement.utils.annotation.Username;
import com.crocostaud.stockmanagement.utils.security.Auth;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final Auth auth;

    public ClientController(ClientService clientService, Auth auth) {
        this.clientService = clientService;
        this.auth = auth;
    }

    @GetMapping
    ResponseEntity<Iterable<ClientDto>> getClients(@Username String username) {
        ShopUser user = auth.getUser(username);
        if (user == null || user.getShop() == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Unauthorized");
        Iterable<ClientDto> clients = clientService.getClients(user.getShop().getId());
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{clientId}")
    ResponseEntity<ClientDto> getClient(@PathVariable Long clientId, @Username String username) {
        ShopUser user = auth.getUser(username);
        if (user == null || user.getShop() == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Unauthorized");
        ClientDto client = clientService.getClient(clientId);
        if (client == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(client);
    }

    @PostMapping
    ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto, @Username String username) {
        ShopUser user = auth.getUser(username);
        if (user == null || user.getShop() == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Unauthorized");
        ClientDto client = clientService.createClient(clientDto, user.getShop().getId());
        return ResponseEntity.ok(client);
    }

    @PutMapping("/{clientId}")
    ResponseEntity<ClientDto> updateClient(@PathVariable Long clientId, @RequestBody ClientDto clientDto, @Username String username) {
        ShopUser user = auth.getUser(username);
        if (user == null || user.getShop() == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Unauthorized");
        ClientDto client = clientService.getClient(clientId);
        if (client == null || clientId != clientDto.getId() || client.getShopId() != user.getShop().getId())
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Unauthorized");
        ClientDto updatedClient = clientService.updateClient(clientDto, user.getShop().getId());
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{clientId}")
    ResponseEntity<Void> deleteClient(@PathVariable Long clientId, @Username String username) {
        ShopUser user = auth.getUser(username);
        if (user == null || user.getShop() == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Unauthorized");
        clientService.removeClient(clientId, user.getShop().getId());
        return ResponseEntity.noContent().build();
    }
}
