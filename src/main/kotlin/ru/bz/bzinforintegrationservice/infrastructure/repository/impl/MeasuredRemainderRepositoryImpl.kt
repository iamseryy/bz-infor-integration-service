package ru.bz.bzinforintegrationservice.infrastructure.repository.impl

import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.MeasuredRemainderDao
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.filter.MeasuredRemainderFilter
import ru.bz.bzinforintegrationservice.domain.repository.MeasuredRemainderRepository
import ru.bz.bzinforintegrationservice.domain.common.pagination.DomainPage
import ru.bz.bzinforintegrationservice.domain.common.pagination.DomainPageRequest

@Component
class MeasuredRemainderRepositoryImpl(
    private val dao: MeasuredRemainderDao
): MeasuredRemainderRepository {
    override fun findPage(
        filter: MeasuredRemainderFilter,
        pageRequest: DomainPageRequest
    ): DomainPage<MeasuredRemainder> = dao.findPage(filter, pageRequest)

    override fun update(
        measuredRemainder: MeasuredRemainder,
        userLogin: String
    ): Int = dao.update(measuredRemainder, userLogin)
}
