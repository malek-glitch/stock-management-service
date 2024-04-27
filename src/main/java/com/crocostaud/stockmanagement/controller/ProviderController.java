package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.ProviderDto;
import com.crocostaud.stockmanagement.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    private final ProviderService providerService;

    @Autowired
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
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(provider);
    }


}
