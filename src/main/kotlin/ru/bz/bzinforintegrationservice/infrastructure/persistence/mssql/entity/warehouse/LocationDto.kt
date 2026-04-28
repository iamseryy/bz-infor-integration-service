package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.warehouse

import ru.bz.bzinforintegrationservice.domain.model.warehouse.Location

data class LocationDto (
    val warehouseCode: String,
    val binCode: String
)

fun LocationDto.toLocation() = Location(
    warehouseCode = warehouseCode,
    binCode = binCode
)