package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainderStatus
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.ALWAYS)
data class MeasuredRemainderDto (
    @field:JsonProperty(JsonFieldsProvider.ID) val id: String,
    @field:JsonProperty(JsonFieldsProvider.CODE) val code: String,
    @field:JsonProperty(JsonFieldsProvider.PROJECT_CODE) val projectCode: String,
    @field:JsonProperty(JsonFieldsProvider.MATERIAL) val material: String,
    @field:JsonProperty(JsonFieldsProvider.LOCATION) val location: MeasuredRemainderLocationDto,
    @field:JsonProperty(JsonFieldsProvider.STATUS) val status: MeasuredRemainderStatus,
    @field:JsonProperty(JsonFieldsProvider.COMMENT) val comment: String,
    @field:JsonProperty(JsonFieldsProvider.DIMENSIONS) val dimensions: MeasuredRemainderDimensionsDto,

    @field:JsonFormat(shape = JsonFormat.Shape.STRING)
    @field:JsonSerialize(using = LocalDateTimeSerializer::class)
    @field:JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonProperty(JsonFieldsProvider.INVENTORY_DATE) val inventoryDate: LocalDateTime
)
fun MeasuredRemainder.toMeasuredRemainderDto() =  MeasuredRemainderDto(
    id = id,
    code = code,
    projectCode = projectCode,
    material = material,
    location = location.toMeasuredRemainderLocationDto(),
    status = status,
    comment = comment,
    dimensions = dimensions.toMeasuredRemainderDimensionsDto(),
    inventoryDate = inventoryDate
)

fun MeasuredRemainderDto.toMeasuredRemainder() =  MeasuredRemainder(
    id = id,
    code = code,
    projectCode = projectCode,
    material = material,
    location = location.toMeasuredRemainderLocation(),
    status = status,
    comment = comment,
    dimensions = dimensions.toMeasuredRemainderDimensions(),
    inventoryDate = inventoryDate
)
