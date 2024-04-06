package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.ProviderDto;
import com.crocostaud.stockmanagement.model.Provider;
import com.crocostaud.stockmanagement.service.ProviderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping("/")
    public ResponseEntity<ProviderDto> create(@RequestBody ProviderDto providerDto){
        ProviderDto provider = providerService.createProvider(providerDto);
        return ResponseEntity.ok(provider);
    }

    @GetMapping("/{providerId}")
    public ResponseEntity<ProviderDto> get(@PathVariable Long providerId){
        ProviderDto provider = providerService.getProvider(providerId);

        if (provider == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(provider);
    }


}