package ru.bz.bzinforintegrationservice.domain.repository

import ru.bz.bzinforintegrationservice.domain.model.filter.SearchInventoryBalanceFilter
import ru.bz.bzinforintegrationservice.domain.model.stock.StockListPagination

interface InventoryRepository {
     fun findInventoryBalanceByFilter(filter: SearchInventoryBalanceFilter): StockListPagination
}