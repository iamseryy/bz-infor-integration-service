package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.container

import java.time.LocalDateTime

data class ContainerDetailStockPositionDto(
    val code: String,
    val warehouseOwnerCode: String,
    val description: String?,
    val statusCode: Int?,
    val warehouseCode: String?,
    val binCode: String?,
    val stockLine: Int?,
    val warehouseOrderOrigin: Int?,
    val warehouseOrderCode: String?,
    val warehouseOrderSet: Int?,
    val warehouseOrderLine: Int?,
    val warehouseOrderSequence: Int?,
    val itemCode: String?,
    val itemDescription: String?,
    val unitCode: String?,
    val unitDescription: String?,
    val lot: String?,
    val quantity: Double?,
    val stockWarehouseCode: String?,
    val stockBinCode: String?,
    val userLoginLastModified: String?,
    val dateLastModified: LocalDateTime?
)


