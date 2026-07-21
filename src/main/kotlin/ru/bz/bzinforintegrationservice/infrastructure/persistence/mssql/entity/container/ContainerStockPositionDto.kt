package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.container

import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item.ItemDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item.toItem
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.order.WarehouseOrderDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.order.toWarehouseOrder
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.location.LocationDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.location.toLocation
import ru.bz.bzinforintegrationservice.domain.model.container.ContainerStockPosition

data class ContainerStockPositionDto(
    val line: Int,
    val warehouseOrder: WarehouseOrderDto,
    val item: ItemDto,
    val lotCode: String,
    val quantity: Double,
    val location: LocationDto
){
    companion object
}

fun ContainerStockPositionDto.toContainerStockPosition() = ContainerStockPosition(
    line = line,
    warehouseOrder = warehouseOrder.toWarehouseOrder(),
    item = item.toItem(),
    lotCode = lotCode,
    quantity = quantity,
    location = location.toLocation()
)

