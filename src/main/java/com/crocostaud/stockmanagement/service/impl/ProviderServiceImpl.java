package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.stock.ProviderDto;
import com.crocostaud.stockmanagement.model.stock.Provider;
import com.crocostaud.stockmanagement.model.stock.Shop;
import com.crocostaud.stockmanagement.repository.ProviderRepository;
import com.crocostaud.stockmanagement.service.ProviderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepo;

    public ProviderServiceImpl(ProviderRepository providerRepo) {
        this.providerRepo = providerRepo;
    }

    @Override
    public ProviderDto createProvider(ProviderDto providerDto) {
        Provider provider = Provider.builder()
                .shop(Shop.builder().id(providerDto.getShopId()).build())
                .name(providerDto.getName())
                .email(providerDto.getEmail())
                .phone(providerDto.getPhone())
                .address(providerDto.getAddress())
                .build();
        Provider savedProvider = providerRepo.save(provider);

        return mapToDto(savedProvider);
    }

    @Override
    public ProviderDto updateProvider(ProviderDto providerDto, Long id) {
        if (!providerRepo.existsById(id))
            throw new RuntimeException("provider not found");

        providerRepo.updateNameAndEmailAndPhoneById(providerDto.getName(), providerDto.getEmail(), providerDto.getPhone(), id);

        Provider updatedProvider = Provider.builder()
                .id(id)
                .name(providerDto.getName())
                .email(providerDto.getName())
                .phone(providerDto.getPhone())
                .address(providerDto.getAddress())
                .build();
        return mapToDto(updatedProvider);
    }

    @Override
    public Provider getProvider(Long id) {
        return providerRepo.findById(id)
                .orElse(null);
    }

    @Override
    public void removeProvider(Long id) {
        providerRepo.deleteById(id);
    }

    @Override
    public List<ProviderDto> getAllProviders(Long shopId) {
        return providerRepo.findByShop_Id(shopId);
    }

    private ProviderDto mapToDto(Provider provider) {
        Long shopId = (provider.getShop() == null) ? null : provider.getShop().getId();

        return ProviderDto.builder()
                .id(provider.getId())
                .name(provider.getName())
                .email(provider.getEmail())
                .phone(provider.getPhone())
                .address(provider.getAddress())
                .shopId(shopId)
                .build();
    }
}
