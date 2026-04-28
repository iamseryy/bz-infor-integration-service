package ru.bz.bzinforintegrationservice.domain.usecases.container

import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.domain.repository.ContainerRepository
import ru.bz.bzinforintegrationservice.domain.model.container.Container


@Component
class UseCaseGetContainerDetailByCode(
    private val containerRepository: ContainerRepository
) {
    operator fun invoke(code: String): Container? = containerRepository.getContainerDetailByCode(code)
}