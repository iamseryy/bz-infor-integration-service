package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.measuredremainder

import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainderStatus
import java.time.LocalDateTime

data class MeasuredRemainderDto(
    val id: String,
    val code: String,
    val projectCode: String,
    val material: String,
    val location: MeasuredRemainderLocationDto,
    val statusCode: Int,
    val comment: String,
    val dimensions: MeasuredRemainderDimensionsDto,
    val inventoryDate: LocalDateTime
)

fun MeasuredRemainderDto.toMeasuredRemainder() = MeasuredRemainder(
    id = id,
    code = code,
    projectCode = projectCode,
    material = material,
    location = location.toMeasuredRemainderLocation(),
    status = MeasuredRemainderStatus.fromNumberOrDefault(statusCode),
    comment = comment,
    dimensions = dimensions.toMeasuredRemainderDimensions(),
    inventoryDate = inventoryDate
)
