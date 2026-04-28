package ru.bz.bzinforintegrationservice.infrastructure.repository.impl

import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.InventoryDao
import ru.bz.bzinforintegrationservice.domain.repository.InventoryRepository
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchInventoryBalanceFilter


@Service
class InventoryRepositoryImpl(
    private val inventoryDao: InventoryDao
): InventoryRepository {
    override fun findInventoryBalanceByFilter(filter: SearchInventoryBalanceFilter) = inventoryDao.findInventoryBalanceByFilter(filter)
}