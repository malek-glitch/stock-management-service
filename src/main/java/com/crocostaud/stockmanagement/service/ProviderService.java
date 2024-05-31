package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.stock.ProviderDto;
import com.crocostaud.stockmanagement.model.stock.Provider;

import java.util.List;

public interface ProviderService {
    ProviderDto createProvider(ProviderDto providerDto);
    ProviderDto updateProvider(ProviderDto providerDto, Long id);

    Provider getProvider(Long id);
    void removeProvider(Long id);

    List<ProviderDto> getAllProviders(Long shopId);
}
