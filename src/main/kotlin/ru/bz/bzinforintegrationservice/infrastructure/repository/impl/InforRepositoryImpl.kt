package ru.bz.bzinforintegrationservice.infrastructure.repository.impl

import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.MeasuredRemainderDao
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchMeasuredRemainderFilter
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.MeasuredRemaindersPagination
import ru.bz.bzinforintegrationservice.domain.repository.MeasuredRemainderRepository

@Component
class InforRepositoryImpl(
    private val measuredRemainderDao: MeasuredRemainderDao
): MeasuredRemainderRepository {
    override fun findByFilter(filter: SearchMeasuredRemainderFilter): MeasuredRemaindersPagination =
        measuredRemainderDao.findByFilter(filter)
}
