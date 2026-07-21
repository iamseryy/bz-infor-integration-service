package ru.bz.bzinforintegrationservice.applications.usecases.container

import org.springframework.stereotype.Component


@Component
data class ContainerUseCases(
    val getContainerByCode: UseCaseGetContainerDetailByCode,
    val findContainersByFilter: UseCaseFindContainersByFilter
)
