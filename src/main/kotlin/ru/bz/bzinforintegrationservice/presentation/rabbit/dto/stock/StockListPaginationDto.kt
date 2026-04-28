package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.stock


import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.stock.StockListPagination
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class StockListPaginationDto(
    @field:JsonProperty(JsonFieldsProvider.STOCK) val stockList: List<StockDto>,
    @field:JsonProperty(JsonFieldsProvider.PAGE) val page: Int,
    @field:JsonProperty(JsonFieldsProvider.PAGE_SIZE) val pageSize: Int
)

fun StockListPagination.toStockListPaginationDto() = StockListPaginationDto(
    stockList = stockList.map { it.toStockDto() },
    page = page,
    pageSize = pageSize
)
