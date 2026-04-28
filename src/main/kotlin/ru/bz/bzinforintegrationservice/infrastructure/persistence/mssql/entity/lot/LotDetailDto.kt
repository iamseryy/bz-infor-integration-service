package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.lot

import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item.ItemDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item.toItem
import ru.bz.bzinforintegrationservice.domain.model.lot.LotDetail

data class LotDetailDto(
    val item: ItemDto,
    val lotCode: String,
    val effectivityUnitCode: Long,
    val lotCodeParent: String,
    val lotCodeSource: String,
)

fun LotDetailDto.toLotDetail() = LotDetail(
    item = item.toItem(),
    lotCode = lotCode,
    effectivityUnitCode = effectivityUnitCode,
    lotCodeParent = lotCodeParent,
    lotCodeSource = lotCodeSource
)
