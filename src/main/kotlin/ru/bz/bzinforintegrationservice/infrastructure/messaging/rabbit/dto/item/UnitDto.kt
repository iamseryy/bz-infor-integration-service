package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.item

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.Item.Unit
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider

@JsonInclude(JsonInclude.Include.ALWAYS)
data class UnitDto(
    @JsonProperty(JsonFieldsProvider.UNIT_CODE) val code: String,
    @JsonProperty(JsonFieldsProvider.UNIT_DESC) val description: String
)

fun Unit.toUnitDto() = UnitDto(
    code = code,
    description = description
)
