package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.stock

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.stock.Stock
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.item.ItemDto
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.item.toItemDto


@JsonInclude(JsonInclude.Include.ALWAYS)
data class StockDto(
    @field:JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String,
    @field:JsonProperty(JsonFieldsProvider.BIN_CODE) val binCode: String,
    @field:JsonProperty(JsonFieldsProvider.ITEM) val item: ItemDto,
    @field:JsonProperty(JsonFieldsProvider.LOT_CODE) val lotCode: String,
    @field:JsonProperty(JsonFieldsProvider.QUANTITY_AVAILABLE) val quantityAvailable: Double,
    @field:JsonProperty(JsonFieldsProvider.QUANTITY_BLOCKED) val quantityBlocked: Double,
    @field:JsonProperty(JsonFieldsProvider.QUANTITY_ALLOCATED) val quantityAllocated: Double
)

fun Stock.toStockDto() = StockDto(
    warehouseCode = warehouseCode,
    binCode = binCode,
    item = item.toItemDto(),
    lotCode = lotCode,
    quantityAvailable = quantityAvailable,
    quantityBlocked = quantityBlocked,
    quantityAllocated = quantityAllocated
)
