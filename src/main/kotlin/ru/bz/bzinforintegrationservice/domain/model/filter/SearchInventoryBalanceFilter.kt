package ru.bz.bzinforintegrationservice.domain.model.filter



data class SearchInventoryBalanceFilter(
    val warehouseCode:String?,
    val binCode: String?,
    val itemCode: String?,
    val lotCode: String?,
    val findLotChildren: Boolean = false,
    val page: Int,
    val pageSize: Int
)
