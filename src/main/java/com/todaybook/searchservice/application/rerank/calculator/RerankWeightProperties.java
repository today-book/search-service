package com.todaybook.searchservice.application.rerank.calculator;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "todaybook.search.rerank.weight")
public record RerankWeightProperties(
    @NotNull @Min(0) @Max(1) Double vector, @NotNull @Min(0) @Max(1) Double emotion) {}
