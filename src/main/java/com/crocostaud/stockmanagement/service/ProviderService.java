package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.ProviderDto;
import com.crocostaud.stockmanagement.model.Shop;

public interface ProviderService {
    ProviderDto createProvider(ProviderDto providerDto);
    ProviderDto updateProvider(ProviderDto providerDto, Long id);
    ProviderDto getProvider(Long id);
    void removeProvider(Long id);

}
