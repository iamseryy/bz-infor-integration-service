package ru.bz.bzinforintegrationservice.domain.usecases.container

import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.domain.repository.ContainerRepository
import ru.bz.bzinforintegrationservice.domain.model.container.ContainersPagination
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchContainerFilter


@Component
class UseCaseFindContainersByFilter(
    private val containerRepository: ContainerRepository
) {
    operator fun invoke(filter: SearchContainerFilter): ContainersPagination = containerRepository.findContainersByFilter(filter)
}