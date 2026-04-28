package ru.bz.bzinforintegrationservice.domain.usecases.measuredRemainder

import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchMeasuredRemainderFilter
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.MeasuredRemaindersPagination
import ru.bz.bzinforintegrationservice.domain.repository.MeasuredRemainderRepository

@Component
class UseCaseFindByFilter(
    private val measuredRemainderRepository: MeasuredRemainderRepository
) {
    operator fun invoke(filter: SearchMeasuredRemainderFilter): MeasuredRemaindersPagination =
        measuredRemainderRepository.findByFilter(filter)
}