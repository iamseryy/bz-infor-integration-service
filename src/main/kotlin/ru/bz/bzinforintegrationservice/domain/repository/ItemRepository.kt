package ru.bz.bzinforintegrationservice.domain.repository

import ru.bz.bzinforintegrationservice.domain.model.Item.Item

interface ItemRepository {
    fun getItemDetail(itemCode: String): Item?
}