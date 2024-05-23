package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.part.MakerDto;
import com.crocostaud.stockmanagement.model.part.Maker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MakerService {
    Maker getMaker(Long makerId);

    Maker saveMaker(MakerDto maker);

    MakerDto updateMaker(MakerDto maker);

    MakerDto deleteMaker(Long makerId);

    List<MakerDto> getAllMakers();
}
