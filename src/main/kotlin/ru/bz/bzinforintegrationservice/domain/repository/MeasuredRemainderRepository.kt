package ru.bz.bzinforintegrationservice.domain.repository

import ru.bz.bzinforintegrationservice.domain.common.pagination.DomainPage
import ru.bz.bzinforintegrationservice.domain.common.pagination.DomainPageRequest
import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.filter.MeasuredRemainderFilter

interface MeasuredRemainderRepository{
    fun findPage(filter: MeasuredRemainderFilter, pageRequest: DomainPageRequest): DomainPage<MeasuredRemainder>
    fun update(measuredRemainder: MeasuredRemainder, userLogin: String): Int
}