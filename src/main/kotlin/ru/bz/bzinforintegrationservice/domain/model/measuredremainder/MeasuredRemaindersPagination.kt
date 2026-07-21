package ru.bz.bzinforintegrationservice.domain.model.measuredremainder

import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainder

data class MeasuredRemaindersPagination(
    val measuredRemainders: List<MeasuredRemainder>,
    val page: Int,
    val pageSize: Int
)
