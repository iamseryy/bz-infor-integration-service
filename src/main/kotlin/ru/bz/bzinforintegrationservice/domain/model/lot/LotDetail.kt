package ru.bz.bzinforintegrationservice.domain.model.lot

import ru.bz.bzinforintegrationservice.domain.model.Item.Item

data class LotDetail(
    val item: Item,
    val lotCode: String,
    val effectivityUnitCode: Long,
    val lotCodeParent: String,
    val lotCodeSource: String,
)
