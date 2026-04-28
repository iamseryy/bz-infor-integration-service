package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.MeasuredRemainder
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider

@JsonInclude(JsonInclude.Include.ALWAYS)
data class MeasuredRemainderDto (
    @field:JsonProperty(JsonFieldsProvider.ID) val id: String,
    @field:JsonProperty(JsonFieldsProvider.REMAINDER) val remainder: String,
    @field:JsonProperty(JsonFieldsProvider.PROJECT_CODE) val projectCode: String,
    @field:JsonProperty(JsonFieldsProvider.MATERIAL) val material: String,
    @field:JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String,
    @field:JsonProperty(JsonFieldsProvider.BIN_CODE) val binCode: String,
    @field:JsonProperty(JsonFieldsProvider.SEQUENCE) val sequence: Int,
    @field:JsonProperty(JsonFieldsProvider.STATUS) val status: Int,
    @field:JsonProperty(JsonFieldsProvider.COMMENT) val comment: String,
    @field:JsonProperty(JsonFieldsProvider.LENGTH) val length: Double,
    @field:JsonProperty(JsonFieldsProvider.WIDTH) val width: Double,
    @field:JsonProperty(JsonFieldsProvider.DEPTH) val depth: Double
)
fun MeasuredRemainder.toMeasuredRemainderDto() =  MeasuredRemainderDto(
    id = id,
    remainder = remainder,
    projectCode = projectCode,
    material = material,
    warehouseCode = warehouseCode,
    binCode = binCode,
    sequence = sequence,
    status = status.number,
    comment = comment,
    length = length,
    width = width,
    depth = depth
)
