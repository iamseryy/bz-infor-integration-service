package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.stock

import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item.ItemDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item.toItem
import ru.bz.bzinforintegrationservice.domain.model.stock.Stock

data class StockDto(
    val warehouseCode: String,
    val binCode: String,
    val item: ItemDto,
    val lotCode: String,
    val quantityAvailable: Double,
    val quantityBlocked: Double,
    val quantityAllocated: Double
)

fun StockDto.toStock() = Stock(
    warehouseCode = warehouseCode,
    binCode = binCode,
    item = item.toItem(),
    lotCode = lotCode,
    quantityAvailable = quantityAvailable,
    quantityBlocked = quantityBlocked,
    quantityAllocated = quantityAllocated
)


