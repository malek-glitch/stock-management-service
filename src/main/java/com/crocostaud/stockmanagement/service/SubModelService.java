package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.stock.SubModelDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubModelService {
    List<SubModelDto> getAllSubModels(Long makerId);

    SubModelDto getSubModelById(Long id);

    SubModelDto createSubModel(SubModelDto subModelDto);

    SubModelDto updateSubModel(SubModelDto subModelDto);

    SubModelDto deleteSubModel(SubModelDto subModelDto);
}
