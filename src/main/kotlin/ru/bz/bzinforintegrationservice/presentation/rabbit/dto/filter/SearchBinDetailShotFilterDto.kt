package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.filter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchBinDetailShotFilter
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class SearchBinDetailShotFilterDto(
    @JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String,
    @JsonProperty(JsonFieldsProvider.BIN_CODE) val binCode: String
)

fun SearchBinDetailShotFilterDto.toSearchBinDetailShotFilter() = SearchBinDetailShotFilter(
    warehouseCode = warehouseCode,
    binCode = binCode
)
