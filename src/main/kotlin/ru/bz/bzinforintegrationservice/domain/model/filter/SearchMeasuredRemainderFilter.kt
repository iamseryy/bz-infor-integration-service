package ru.bz.bzinforintegrationservice.domain.model.filter

import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.MeasuredRemainderStatus

data class SearchMeasuredRemainderFilter (
    val id: String?,
    val remainderLike: String?,
    val projectCode: String?,
    val materialLike: String?,
    val warehouseCode: String?,
    val binCode: String?,
    val status: MeasuredRemainderStatus?,
    val comment: String?,
    val lengthFrom: Double?,
    val widthFrom: Double?,
    val depth: Double?,
    val page: Int,
    val pageSize: Int
)