package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.part.DescriptionDto;

import java.util.List;

public interface DescriptionService {
    List<DescriptionDto> getDescriptionsByPart(long partId);
}
