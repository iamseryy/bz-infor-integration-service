package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.RabbitMessage

@JsonInclude(JsonInclude.Include.ALWAYS)
data class FindLocationsByWarehouseCodeRequestMessage(
    @field:JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String?
): RabbitMessage(messageType = "LOCATIONS_BY_WAREHOUSE_CODE_SEARCH_REQUEST")
