package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.warehouse

import ru.bz.bzinforintegrationservice.domain.model.warehouse.Warehouse

data class WarehouseDto(
    val code: String,
    val description: String,
)

fun WarehouseDto.toWarehouse() = Warehouse(
    code = code,
    description = description
)
