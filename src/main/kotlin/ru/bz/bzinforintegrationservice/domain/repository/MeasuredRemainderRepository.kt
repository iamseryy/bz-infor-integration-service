package ru.bz.bzinforintegrationservice.domain.repository

import ru.bz.bzinforintegrationservice.domain.model.filter.SearchMeasuredRemainderFilter
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.MeasuredRemaindersPagination

fun interface MeasuredRemainderRepository{
    fun findByFilter(filter: SearchMeasuredRemainderFilter): MeasuredRemaindersPagination
}