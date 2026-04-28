package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.warehouse

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Location
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class LocationDto (
    @JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String,
    @JsonProperty(JsonFieldsProvider.BIN_CODE) val binCode: String
)

fun Location.toLocationDto() = LocationDto(
    warehouseCode = warehouseCode,
    binCode = binCode
)