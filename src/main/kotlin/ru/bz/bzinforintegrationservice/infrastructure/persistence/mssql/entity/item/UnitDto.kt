package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item

import ru.bz.bzinforintegrationservice.domain.model.Item.Unit

data class UnitDto(
    val code: String,
    val description: String
)

fun UnitDto.toUnit() = Unit(
    code = code,
    description = description
)