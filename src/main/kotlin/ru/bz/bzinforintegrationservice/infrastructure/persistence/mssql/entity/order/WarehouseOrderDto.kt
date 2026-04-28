package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.order

import ru.bz.bzinforintegrationservice.domain.model.order.WarehouseOrder

data class WarehouseOrderDto(
    val origin: Int,
    val code: String,
    val set: Int,
    val line: Int,
    val sequence: Int,
)

fun WarehouseOrderDto.toWarehouseOrder() = WarehouseOrder(
    origin = origin,
    code = code,
    set = set,
    line = line,
    sequence = sequence
)
