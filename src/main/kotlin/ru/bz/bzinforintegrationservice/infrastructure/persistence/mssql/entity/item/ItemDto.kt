package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item

import ru.bz.bzinforintegrationservice.domain.model.Item.Item

data class ItemDto(
    val code: String,
    val description: String,
    val unit: UnitDto
)

fun ItemDto.toItem() = Item(
    code = code,
    description = description,
    unit = unit.toUnit()
)
