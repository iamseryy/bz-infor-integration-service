package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.measuredremainder

import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainderLocation

data class MeasuredRemainderLocationDto(
    val warehouseCode: String,
    val binCode: String,
    val sequence: Int
)

fun MeasuredRemainderLocationDto.toMeasuredRemainderLocation() = MeasuredRemainderLocation(
    warehouseCode = warehouseCode,
    binCode = binCode,
    sequence = sequence
)

fun MeasuredRemainderLocation.toMeasuredRemainderLocationDto() = MeasuredRemainderLocationDto(
    warehouseCode = warehouseCode,
    binCode = binCode,
    sequence = sequence
)
