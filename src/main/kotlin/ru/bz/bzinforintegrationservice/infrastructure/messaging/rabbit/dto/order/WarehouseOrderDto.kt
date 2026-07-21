package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.order

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.order.WarehouseOrder
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider

@JsonInclude(JsonInclude.Include.ALWAYS)
data class WarehouseOrderDto(
    @JsonProperty(JsonFieldsProvider.ORIGIN) val origin: Int,
    @JsonProperty(JsonFieldsProvider.CODE) val code: String,
    @JsonProperty(JsonFieldsProvider.SET) val set: Int,
    @JsonProperty(JsonFieldsProvider.LINE) val line: Int,
    @JsonProperty(JsonFieldsProvider.SEQUENCE) val sequence: Int
)

fun WarehouseOrder.toWarehouseOrderDto() = WarehouseOrderDto(
    origin = origin,
    code = code,
    set = set,
    line = line,
    sequence = sequence
)
