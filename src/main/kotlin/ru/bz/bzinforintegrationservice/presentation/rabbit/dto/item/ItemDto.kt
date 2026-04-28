package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.item

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.Item.Item
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider

@JsonInclude(JsonInclude.Include.ALWAYS)
data class ItemDto(
    @JsonProperty(JsonFieldsProvider.ITEM_CODE) val code: String,
    @JsonProperty(JsonFieldsProvider.ITEM_DESC) val description: String,
    @JsonProperty(JsonFieldsProvider.UNIT) val unit: UnitDto
)

fun Item.toItemDto() = ItemDto(
    code = code,
    description = description,
    unit = unit.toUnitDto()
)
