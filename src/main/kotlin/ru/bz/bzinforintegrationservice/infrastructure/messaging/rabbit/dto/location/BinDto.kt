package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.location

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Bin
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider

@JsonInclude(JsonInclude.Include.ALWAYS)
data class BinDto(
    @JsonProperty(JsonFieldsProvider.BIN_CODE) val code: String,
    @JsonProperty(JsonFieldsProvider.BIN_DESC) val description: String,
    @JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String
)

fun Bin.toBinDto() = BinDto(
    code = code,
    description = description,
    warehouseCode = warehouseCode
)