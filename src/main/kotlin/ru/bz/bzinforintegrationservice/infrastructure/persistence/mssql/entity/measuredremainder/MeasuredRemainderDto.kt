package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.measuredremainder

import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.MeasuredRemainder
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.MeasuredRemainderStatus

data class MeasuredRemainderDto(
    val id: String,
    val remainder: String,
    val projectCode: String,
    val material: String,
    val warehouseCode: String,
    val binCode: String,
    val sequence: Int,
    val statusCode: Int,
    val comment: String,
    val length: Double,
    val width: Double,
    val depth: Double
)

fun MeasuredRemainderDto.toMeasuredRemainder() = MeasuredRemainder(
    id = id,
    remainder = remainder,
    projectCode = projectCode,
    material = material,
    warehouseCode = warehouseCode,
    binCode = binCode,
    sequence = sequence,
    status = MeasuredRemainderStatus.getByNumber(statusCode),
    comment = comment,
    length = length,
    width = width,
    depth = depth
)
