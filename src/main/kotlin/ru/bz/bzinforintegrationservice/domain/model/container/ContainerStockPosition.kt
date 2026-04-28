package ru.bz.bzinforintegrationservice.domain.model.container

import ru.bz.bzinforintegrationservice.domain.model.Item.Item
import ru.bz.bzinforintegrationservice.domain.model.order.WarehouseOrder
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Location

data class ContainerStockPosition(
    val line: Int,
    val warehouseOrder: WarehouseOrder,
    val item: Item,
    val lotCode: String,
    val quantity: Double,
    val location: Location
)
