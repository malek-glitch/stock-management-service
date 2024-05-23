package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.part.ModelDto;
import com.crocostaud.stockmanagement.dto.stock.SubModelDto;
import com.crocostaud.stockmanagement.model.part.Maker;

import java.util.List;

public interface CarService {

    Maker getMaker(Long makerId);

    List<Maker> getAllMakers();

    List<ModelDto> getModels(Long makerId);

    ModelDto getModel(Long modelId);

    List<SubModelDto> getSubModels(Long modelId);

    SubModelDto getSubModel(Long subModelId);

}
