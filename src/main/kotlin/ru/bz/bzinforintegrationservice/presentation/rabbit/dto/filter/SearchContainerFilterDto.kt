package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.filter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.container.ContainerStatus
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchContainerFilter
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchContainerFilterDto(
    @field:JsonProperty(JsonFieldsProvider.CONTAINER_CODE) val containerCode: String?,
    @field:JsonProperty(JsonFieldsProvider.WAREHOUSE_OWNER_CODE) val warehouseOwnerCode: String?,
    @field:JsonProperty(JsonFieldsProvider.CONTAINER_DESCRIPTION) val containerDescription: String?,
    @field:JsonProperty(JsonFieldsProvider.CONTAINER_STATUS) val status: ContainerStatus?,
    @field:JsonProperty(JsonFieldsProvider.CONTAINER_LOCATION_WAREHOUSE_CODE) val containerLocationWarehouseCode: String?,
    @field:JsonProperty(JsonFieldsProvider.CONTAINER_LOCATION_BIN_CODE) val containerLocationBinCode: String?,
    @field:JsonProperty(JsonFieldsProvider.WAREHOUSE_ORDER_CODE) val warehouseOrderCode: String?,
    @field:JsonProperty(JsonFieldsProvider.ITEM_CODE) val itemCode: String?,
    @field:JsonProperty(JsonFieldsProvider.LOT_CODE) val lotCode: String?,
    @field:JsonProperty(JsonFieldsProvider.STOCK_POSITION_LOCATION_WAREHOUSE_CODE) val stockPositionLocationWarehouseCode: String?,
    @field:JsonProperty(JsonFieldsProvider.STOCK_POSITION_LOCATION_BIN_CODE) val stockPositionLocationBinCode: String?,
    @field:JsonProperty(JsonFieldsProvider.PAGE) val page: Int = 0,
    @field:JsonProperty(JsonFieldsProvider.PAGE_SIZE) val pageSize: Int = 10
)

fun SearchContainerFilterDto.toSearchContainerFilter() = SearchContainerFilter (
    containerCode = containerCode,
    warehouseOwnerCode = warehouseOwnerCode,
    containerDescription = containerDescription,
    status = status,
    containerLocationWarehouseCode = containerLocationWarehouseCode,
    containerLocationBinCode = containerLocationBinCode,
    warehouseOrderCode = warehouseOrderCode,
    itemCode = itemCode,
    lotCode = lotCode,
    stockPositionLocationWarehouseCode = stockPositionLocationWarehouseCode,
    stockPositionLocationBinCode = stockPositionLocationBinCode,
    page = page,
    pageSize = pageSize
)
