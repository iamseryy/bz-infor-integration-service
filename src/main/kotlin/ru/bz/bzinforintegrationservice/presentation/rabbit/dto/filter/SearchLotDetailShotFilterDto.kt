package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.filter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchLotDetailShotFilter
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class SearchLotDetailShotFilterDto(
    @JsonProperty(JsonFieldsProvider.ITEM_CODE) val itemCode: String,
    @JsonProperty(JsonFieldsProvider.LOT_CODE) val lotCode: String
)

fun SearchLotDetailShotFilterDto.toSearchLotDetailShotFilter() = SearchLotDetailShotFilter(
    itemCode = itemCode,
    lotCode = lotCode
)
