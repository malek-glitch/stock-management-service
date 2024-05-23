package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.part.ModelDto;

import java.util.List;

public interface ModelService {
    ModelDto getModel(Long id);

    ModelDto addModel(ModelDto modelDto);

    ModelDto updateModel(ModelDto modelDto);

    ModelDto deleteModel(ModelDto modelDto);

    List<ModelDto> getAllModels();
}
