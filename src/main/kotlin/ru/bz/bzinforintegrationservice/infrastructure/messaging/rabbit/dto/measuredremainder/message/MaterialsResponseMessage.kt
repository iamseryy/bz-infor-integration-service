package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.RabbitMessage


@JsonInclude(JsonInclude.Include.ALWAYS)
data class MaterialsResponseMessage(
    @field:JsonProperty(JsonFieldsProvider.MATERIALS) val materials: List<String>,
): RabbitMessage(messageType = "MATERIALS_SEARCH_RESPONSE")
