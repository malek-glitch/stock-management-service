package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.part.CategoryDto;
import com.crocostaud.stockmanagement.dto.part.ModelDto;
import com.crocostaud.stockmanagement.dto.stock.SubModelDto;
import com.crocostaud.stockmanagement.model.part.Category;
import com.crocostaud.stockmanagement.model.part.Maker;
import com.crocostaud.stockmanagement.model.part.Model;
import com.crocostaud.stockmanagement.model.part.SubModel;
import com.crocostaud.stockmanagement.repository.CategoryRepository;
import com.crocostaud.stockmanagement.repository.MakerRepository;
import com.crocostaud.stockmanagement.repository.ModelRepository;
import com.crocostaud.stockmanagement.repository.SubModelRepository;
import com.crocostaud.stockmanagement.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final MakerRepository makerRepo;
    private final ModelRepository modelRepo;
    private final SubModelRepository subModelRepo;
    private final CategoryRepository categoryRepo;

    CarServiceImpl(MakerRepository makerRepo, ModelRepository modelRepo, SubModelRepository subModelRepo, CategoryRepository categoryRepo) {
        this.makerRepo = makerRepo;
        this.modelRepo = modelRepo;
        this.subModelRepo = subModelRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Maker getMaker(Long makerId) {
        Optional<Maker> makerOptional = makerRepo.findById(makerId);
        return makerOptional.orElse(null);
    }

    @Override
    public List<Maker> getAllMakers() {
        return makerRepo.findAll();
    }

    @Override
    public List<ModelDto> getModels(Long makerId) {
        if (getMaker(makerId) == null)
            return null;
        return modelRepo.findByMaker_Id(makerId);
    }

    @Override
    public ModelDto getModel(Long modelId) {
        Model model = modelRepo.findById(modelId).orElse(null);
        return mapToDto(model);
    }

    @Override
    public List<SubModelDto> getSubModels(Long modelId) {
        return subModelRepo.findByModel_Id(modelId);
    }

    @Override
    public SubModelDto getSubModel(Long subModelId) {
        SubModel submodel = subModelRepo.findById(subModelId).orElse(null);
        return mapToDto(submodel);
    }

    @Override
    public List<String> getCategories() {
        return categoryRepo.findDistinctCategoryName();
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepo.findAll().stream().map(this::mapToDto).toList();
    }

    private ModelDto mapToDto(Model model) {
        if (model == null) return null;
        return ModelDto.builder()
                .id(model.getId())
                .name(model.getName())
                .description(model.getDescription())
                .ref(model.getRef())
                .build();
    }

    private SubModelDto mapToDto(SubModel submodel) {
        if (submodel == null) return null;
        return SubModelDto.builder()
                .id(submodel.getId())
                .modelId(submodel.getModel().getId())
                .description(submodel.getDescription())
                .energyType(submodel.getEnergyType())
                .build();
    }

    private CategoryDto mapToDto(Category category) {
        if (category == null)
            return null;
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
