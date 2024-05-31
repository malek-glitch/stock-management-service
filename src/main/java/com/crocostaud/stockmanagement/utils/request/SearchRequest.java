package com.crocostaud.stockmanagement.utils.request;

public record SearchRequest(String text, Long modelId, Long submodelId, Long categoryId, Long partId) {
}
