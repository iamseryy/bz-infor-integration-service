package ru.bz.bzinforintegrationservice.domain.usecases.measuredRemainder

import org.springframework.stereotype.Component

@Component
data class MeasuredRemainderUseCases(
    val findByFilter: UseCaseFindByFilter
)
