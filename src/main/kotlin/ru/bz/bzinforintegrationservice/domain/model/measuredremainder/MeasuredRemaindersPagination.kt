package ru.bz.bzinforintegrationservice.domain.model.measuredremainder

data class MeasuredRemaindersPagination(
    val measuredRemainders: List<MeasuredRemainder>,
    val page: Int,
    val pageSize: Int
)
