package ru.bz.bzinforintegrationservice.domain.model.stock

import ru.bz.bzinforintegrationservice.domain.model.Item.Item

data class Stock(
    val warehouseCode: String,
    val binCode: String,
    val item: Item,
    val lotCode: String,
    val quantityAvailable: Double,
    val quantityBlocked: Double,
    val quantityAllocated: Double
)
