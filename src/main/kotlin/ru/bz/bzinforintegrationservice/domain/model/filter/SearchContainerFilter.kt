package ru.bz.bzinforintegrationservice.domain.model.filter

import ru.bz.bzinforintegrationservice.domain.model.container.ContainerStatus

data class SearchContainerFilter(
    val containerCode: String?,
    val warehouseOwnerCode: String?,
    val containerDescription: String?,
    val status: ContainerStatus?,
    val containerLocationWarehouseCode: String?,
    val containerLocationBinCode: String?,
    val warehouseOrderCode: String?,
    val itemCode: String?,
    val lotCode: String?,
    val stockPositionLocationWarehouseCode: String?,
    val stockPositionLocationBinCode: String?,
    val page: Int = 0,
    val pageSize: Int = 10
)
