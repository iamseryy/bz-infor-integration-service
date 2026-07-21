package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.location

import ru.bz.bzinforintegrationservice.domain.model.warehouse.Warehouse

data class WarehouseDto(
    val code: String,
    val description: String,
)

fun WarehouseDto.toWarehouse() = Warehouse(
    code = code,
    description = description
)
