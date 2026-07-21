package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainderDimensions
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class MeasuredRemainderDimensionsDto(
    @field:JsonProperty(JsonFieldsProvider.LENGTH) val length: Double,
    @field:JsonProperty(JsonFieldsProvider.WIDTH) val width: Double,
    @field:JsonProperty(JsonFieldsProvider.THICKNESS) val thickness: Double
)

fun MeasuredRemainderDimensionsDto.toMeasuredRemainderDimensions() = MeasuredRemainderDimensions(
    length = length,
    width = width,
    thickness = thickness
)

fun MeasuredRemainderDimensions.toMeasuredRemainderDimensionsDto() = MeasuredRemainderDimensionsDto(
    length = length,
    width = width,
    thickness = thickness
)

