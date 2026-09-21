package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.RabbitMessage


@JsonInclude(JsonInclude.Include.ALWAYS)
data class WarehouseCodesResponseMessage(
    @field:JsonProperty(JsonFieldsProvider.WAREHOUSE_CODES) val warehouseCodes: List<String>,
): RabbitMessage(messageType = "WAREHOUSE_CODES_SEARCH_RESPONSE")
