package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.item

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class ItemMessageDto(
    @JsonProperty(JsonFieldsProvider.ITEM) val item: ItemDto?
)
