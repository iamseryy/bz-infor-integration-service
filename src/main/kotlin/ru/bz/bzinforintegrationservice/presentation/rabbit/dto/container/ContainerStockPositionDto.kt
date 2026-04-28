package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.container

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.container.ContainerStockPosition
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.item.ItemDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.item.toItemDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.order.WarehouseOrderDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.order.toWarehouseOrderDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.warehouse.LocationDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.warehouse.toLocationDto


@JsonInclude(JsonInclude.Include.ALWAYS)
data class ContainerStockPositionDto(
    @JsonProperty(JsonFieldsProvider.LINE) val line: Int,
    @JsonProperty(JsonFieldsProvider.WAREHOUSE_ORDER) val warehouseOrder: WarehouseOrderDto,
    @JsonProperty(JsonFieldsProvider.ITEM) val item: ItemDto,
    @JsonProperty(JsonFieldsProvider.LOT_CODE) val lotCode: String,
    @JsonProperty(JsonFieldsProvider.QUANTITY) val quantity: Double,
    @JsonProperty(JsonFieldsProvider.LOCATION) val location: LocationDto
)

fun ContainerStockPosition.toContainerStockPositionDto() = ContainerStockPositionDto(
    line = line,
    warehouseOrder = warehouseOrder.toWarehouseOrderDto(),
    item = item.toItemDto(),
    lotCode = lotCode,
    quantity = quantity,
    location = location.toLocationDto()
)
