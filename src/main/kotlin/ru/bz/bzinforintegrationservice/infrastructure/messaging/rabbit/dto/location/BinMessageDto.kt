package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.location

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class BinMessageDto(
    @JsonProperty(JsonFieldsProvider.BIN) val location: BinDto?
)
