package ru.bz.bzinforintegrationservice.infrastructure.repository.impl

import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.ItemDao
import ru.bz.bzinforintegrationservice.domain.repository.ItemRepository
import ru.bz.bzinforintegrationservice.domain.model.Item.Item


@Service
class ItemRepositoryImpl(
    private val itemDao: ItemDao
): ItemRepository {
    override fun getItemDetail(itemCode: String): Item? = itemDao.getItemDetail(itemCode)
}