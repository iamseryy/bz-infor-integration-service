package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.lot

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class LotDetailMessageDto(
    @JsonProperty(JsonFieldsProvider.LOT) val lot: LotDetailDto?
)
