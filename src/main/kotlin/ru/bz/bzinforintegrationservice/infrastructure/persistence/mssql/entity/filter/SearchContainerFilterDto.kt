package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.filter

import ru.bz.bzinforintegrationservice.domain.model.container.ContainerStatus
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchContainerFilter

data class SearchContainerFilterDto(
    val containerCode: String? = null,
    val warehouseOwnerCode: String? = null,
    val containerDescription: String? = null,
    val status: ContainerStatus? = null,
    val containerLocationWarehouseCode: String? = null,
    val containerLocationBinCode: String? = null,
    val warehouseOrderCode: String? = null,
    val itemCode: String? = null,
    val lotCode: String? = null,
    val stockPositionLocationWarehouseCode: String? = null,
    val stockPositionLocationBinCode: String? = null,
    val page: Int = 0,
    val pageSize: Int = 10
)

fun SearchContainerFilter.toSearchContainerFilterDto() = SearchContainerFilterDto (
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
