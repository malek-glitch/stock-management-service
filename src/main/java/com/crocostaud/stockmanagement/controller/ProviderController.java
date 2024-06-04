package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.stock.ProviderDto;
import com.crocostaud.stockmanagement.model.stock.Provider;
import com.crocostaud.stockmanagement.model.stock.ShopUser;
import com.crocostaud.stockmanagement.service.ProviderService;
import com.crocostaud.stockmanagement.utils.annotation.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    ResponseEntity<Iterable<ProviderDto>> getAll(@User ShopUser user) {
        if (user == null || user.getShop() == null)
            return ResponseEntity.noContent().build();
        List<ProviderDto> allProviders = providerService.getAllProviders(user.getShop().getId());
        return ResponseEntity.ok(allProviders);
    }

    @GetMapping("/{providerId}")
    public ResponseEntity<Provider> get(@PathVariable Long providerId, @User ShopUser user) {

        Provider provider = providerService.getProvider(providerId);

        if (provider == null || user.getShop() == null || !Objects.equals(provider.getShop().getId(), user.getShop().getId()))
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(provider);
    }

    @PostMapping
    public ResponseEntity<ProviderDto> create(@RequestBody ProviderDto providerDto, @User ShopUser user) {

        if (user == null || user.getShop() == null)
            return ResponseEntity.badRequest().build();
        providerDto.setShopId(user.getShop().getId());
        ProviderDto provider = providerService.createProvider(providerDto);
        return ResponseEntity.ok(provider);
    }

    @PutMapping("/{providerId}")
    ResponseEntity<ProviderDto> update(@PathVariable Long providerId, @RequestBody ProviderDto providerDto, @User ShopUser user) {

        if (user == null || user.getShop() == null)
            return ResponseEntity.badRequest().build();
        if (!Objects.equals(providerDto.getShopId(), user.getShop().getId()))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(providerService.updateProvider(providerDto, providerId));
    }

    @DeleteMapping("/{providerId}")
    ResponseEntity<ProviderDto> delete(@PathVariable Long providerId, @User ShopUser user) {

        if (user == null || user.getShop() == null)
            return ResponseEntity.badRequest().build();
        Provider provider = providerService.getProvider(providerId);
        if (provider == null || !Objects.equals(provider.getShop().getId(), user.getShop().getId()))
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Unauthorized");
        providerService.removeProvider(providerId);
        return ResponseEntity.noContent().build();
    }


}
