package ru.bz.bzinforintegrationservice.applications.usecases.mechanicalpart

import org.springframework.stereotype.Component


@Component
data class MechanicalpartUseCases(
    val reportOperation: ReportOperationUseCase
)
