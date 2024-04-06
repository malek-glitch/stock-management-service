package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.ProviderDto;
import com.crocostaud.stockmanagement.model.Provider;
import com.crocostaud.stockmanagement.model.Shop;
import com.crocostaud.stockmanagement.repository.ProviderRepository;
import com.crocostaud.stockmanagement.service.ProviderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {

    private  final ProviderRepository providerRepo;

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
                .build();
        return mapToDto(updatedProvider);
    }

    @Override
    public ProviderDto getProvider(Long id) {
        Optional<Provider> providerOptional = providerRepo.findById(id);
        return providerOptional.map(this::mapToDto).orElse(null);
    }

    @Override
    public void removeProvider(Long id) {
        providerRepo.deleteById(id);
    }
    private ProviderDto mapToDto(Provider provider){

        return ProviderDto.builder()
                .id(provider.getId())
                .name(provider.getName())
                .email(provider.getEmail())
                .phone(provider.getPhone())
                .shopId(provider.getShop().getId())
                .build();
    }
}
