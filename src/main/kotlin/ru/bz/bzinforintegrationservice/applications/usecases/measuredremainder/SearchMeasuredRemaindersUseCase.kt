package ru.bz.bzinforintegrationservice.applications.usecases.measuredremainder

import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.domain.common.pagination.DomainPage
import ru.bz.bzinforintegrationservice.domain.common.pagination.DomainPageRequest
import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.filter.MeasuredRemainderFilter
import ru.bz.bzinforintegrationservice.domain.repository.MeasuredRemainderRepository

@Component
class SearchMeasuredRemaindersUseCase(
    private val repository: MeasuredRemainderRepository
) {
    operator fun invoke(
        filter: MeasuredRemainderFilter,
        pageRequest: DomainPageRequest
    ): DomainPage<MeasuredRemainder> = repository.findPage(filter, pageRequest)
}