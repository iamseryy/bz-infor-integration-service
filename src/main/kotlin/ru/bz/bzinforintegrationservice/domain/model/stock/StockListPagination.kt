package ru.bz.bzinforintegrationservice.domain.model.stock

data class StockListPagination(
    val stockList: List<Stock>,
    val page: Int,
    val pageSize: Int
)
