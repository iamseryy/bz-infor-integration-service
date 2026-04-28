package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.warehouse

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class BinMessageDto(
    @JsonProperty(JsonFieldsProvider.BIN) val location: BinDto?
)
