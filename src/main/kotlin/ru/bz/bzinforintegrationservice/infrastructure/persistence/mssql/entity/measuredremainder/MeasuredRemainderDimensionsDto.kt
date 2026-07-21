package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.measuredremainder

import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainderDimensions

data class MeasuredRemainderDimensionsDto(
    val length: Double,
    val width: Double,
    val thickness: Double
)

fun MeasuredRemainderDimensionsDto.toMeasuredRemainderDimensions() = MeasuredRemainderDimensions(
    length = length,
    width = width,
    thickness = thickness
)
