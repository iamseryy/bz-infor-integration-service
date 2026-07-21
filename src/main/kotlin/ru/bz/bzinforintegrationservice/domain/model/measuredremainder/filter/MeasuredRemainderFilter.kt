package ru.bz.bzinforintegrationservice.domain.model.measuredremainder.filter

import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainderStatus

data class MeasuredRemainderFilter (
    val id: String?,
    val codeLike: String?,
    val projectCode: String?,
    val materialLike: String?,
    val warehouseCode: String?,
    val binCode: String?,
    val status: MeasuredRemainderStatus?,
    val lengthFrom: Double?,
    val widthFrom: Double?,
    val thicknessFrom: Double?
)