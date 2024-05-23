package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.part.ModelDto;
import com.crocostaud.stockmanagement.dto.stock.SubModelDto;
import com.crocostaud.stockmanagement.model.part.Maker;
import com.crocostaud.stockmanagement.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/makers")
    public ResponseEntity<List<Maker>> makers() {
        return ResponseEntity.ok(carService.getAllMakers());
    }

    @GetMapping("/maker/{makerId}")
    public ResponseEntity<Maker> maker(@PathVariable Long makerId) {
        Maker maker = carService.getMaker(makerId);
        if (maker == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(maker);
    }

    @GetMapping("/maker-{makerId}/models")
    public ResponseEntity<List<ModelDto>> models(@PathVariable Long makerId) {
        List<ModelDto> models = carService.getModels(makerId);
        if (models == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(models);
    }

    @GetMapping("/model/{modelId}")
    public ResponseEntity<ModelDto> model(@PathVariable Long modelId) {
        ModelDto model = carService.getModel(modelId);
        if (model == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(model);
    }

    @GetMapping("/model-{modelId}/submodels")
    public ResponseEntity<List<SubModelDto>> submodels(@PathVariable Long modelId) {
        List<SubModelDto> models = carService.getSubModels(modelId);
        if (models == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(models);
    }

    @GetMapping("/submodel/{submodelId}")
    public ResponseEntity<SubModelDto> submodel(@PathVariable Long submodelId) {
        SubModelDto subModel = carService.getSubModel(submodelId);
        if (subModel == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(subModel);
    }
}
