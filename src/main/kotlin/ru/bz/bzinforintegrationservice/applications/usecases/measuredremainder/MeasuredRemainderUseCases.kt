package ru.bz.bzinforintegrationservice.applications.usecases.measuredremainder

import org.springframework.stereotype.Component

@Component
data class MeasuredRemainderUseCases(
    val search: SearchMeasuredRemaindersUseCase,
    val update: UpdateMeasuredRemaindersUseCase
)