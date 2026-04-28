package ru.bz.bzinforintegrationservice.domain.repository

import ru.bz.bzinforintegrationservice.domain.model.container.Container
import ru.bz.bzinforintegrationservice.domain.model.container.ContainersPagination
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchContainerFilter

interface ContainerRepository {
    fun getContainerDetailByCode(code: String): Container?
    fun findContainersByFilter(filter: SearchContainerFilter): ContainersPagination
}