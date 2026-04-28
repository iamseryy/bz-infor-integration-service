package ru.bz.bzinforintegrationservice.domain.usecases.mechanicalpart

import org.springframework.stereotype.Component


@Component
data class MechanicalpartUseCases(
    val reportOperation: ReportOperationUseCase
)
