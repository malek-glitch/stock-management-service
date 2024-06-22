package com.crocostaud.stockmanagement.controller;

import com.crocostaud.stockmanagement.dto.part.PartDetail;
import com.crocostaud.stockmanagement.dto.part.PartDto;
import com.crocostaud.stockmanagement.service.PartService;
import com.crocostaud.stockmanagement.service.SearchService;
import com.crocostaud.stockmanagement.utils.request.SearchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;
    private final PartService partService;

    public SearchController(SearchService searchService, PartService partService) {
        this.searchService = searchService;
        this.partService = partService;
    }

    @PostMapping
    public ResponseEntity<List<PartDto>> search(@RequestBody SearchRequest search) {
        if (!isTextEmpty(search)) {
            if (search.submodelId() != null)
                return ResponseEntity.ok(searchService.searchBySubmodel(search.submodelId(), search.text()));

            if (search.modelId() != null)
                return ResponseEntity.ok(searchService.searchByModel(search.modelId(), search.text()));
        }

        if (search.categoryId() != null) {
            if (search.modelId() != null)
                return ResponseEntity.ok(searchService.searchByCategoryAndModel(search.categoryId(), search.modelId()));

            if (search.submodelId() != null)
                return ResponseEntity.ok(searchService.searchByCategoryAndSubmodel(search.categoryId(), search.submodelId()));

            return ResponseEntity.ok(searchService.searchByCategory(search.categoryId(), search.text()));
        }

        if (search.submodelId() != null)
            return ResponseEntity.ok(searchService.searchBySubmodel(search.submodelId()));
        if (search.modelId() != null)
            return ResponseEntity.ok(searchService.searchByModel(search.modelId()));
        if (!isTextEmpty(search))
            return ResponseEntity.ok(searchService.searchByText(search.text()));

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/equivalent/{partId}")
    public ResponseEntity<List<PartDto>> searchEquivalent(@PathVariable("partId") Long partId) {
        List<PartDto> equivalentPart = searchService.searchEquivalentPart(partId);
        if (equivalentPart == null || equivalentPart.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(equivalentPart);
    }

    @GetMapping("part/{id}")
    public ResponseEntity<PartDetail> searchPart(@PathVariable("id") Long id) {
        PartDetail partDetail = partService.getPartDetail(id);
        return ResponseEntity.ok(partDetail);
    }

    private boolean isTextEmpty(SearchRequest search) {
        return search.text() == null || search.text().trim().isEmpty();
    }

}


