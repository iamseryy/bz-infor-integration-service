package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainderLocation
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class MeasuredRemainderLocationDto(
    @JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String,
    @JsonProperty(JsonFieldsProvider.BIN_CODE) val binCode: String,
    @JsonProperty(JsonFieldsProvider.SEQUENCE) val sequence: Int
)

fun MeasuredRemainderLocation.toMeasuredRemainderLocationDto() = MeasuredRemainderLocationDto(
    warehouseCode = warehouseCode,
    binCode = binCode,
    sequence = sequence
)

fun MeasuredRemainderLocationDto.toMeasuredRemainderLocation() = MeasuredRemainderLocation(
    warehouseCode = warehouseCode,
    binCode = binCode,
    sequence = sequence
)