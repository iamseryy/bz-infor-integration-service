package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.item

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class ItemMessageDto(
    @JsonProperty(JsonFieldsProvider.ITEM) val item: ItemDto?
)
