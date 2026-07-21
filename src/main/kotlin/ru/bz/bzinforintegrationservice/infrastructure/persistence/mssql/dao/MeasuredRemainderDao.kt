package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao

import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.filter.MeasuredRemainderFilter
import ru.bz.bzinforintegrationservice.domain.common.pagination.DomainPage
import ru.bz.bzinforintegrationservice.domain.common.pagination.DomainPageRequest


interface MeasuredRemainderDao {
    fun findPage(filter: MeasuredRemainderFilter, pageRequest: DomainPageRequest): DomainPage<MeasuredRemainder>
    fun update(measuredRemainder: MeasuredRemainder, userLogin: String): Int
    fun findBinCodesByWarehouseCode(warehouseCode: String): List<String>
}