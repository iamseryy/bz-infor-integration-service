package ru.bz.bzinforintegrationservice.infrastructure.repository.impl

import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.ContainerDao
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.container.toContainer
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.container.toContainersPagination
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.filter.toSearchContainerFilterDto
import ru.bz.bzinforintegrationservice.domain.repository.ContainerRepository
import ru.bz.bzinforintegrationservice.domain.model.container.Container
import ru.bz.bzinforintegrationservice.domain.model.container.ContainersPagination
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchContainerFilter


@Component
class ContainerRepositoryImpl(
    private val containerDao: ContainerDao
): ContainerRepository {
    override fun getContainerDetailByCode(code: String): Container? = containerDao.getContainerDetailByCode(code)?.toContainer()

    override fun findContainersByFilter(filter: SearchContainerFilter): ContainersPagination =
        containerDao.findContainersDetailByFilter(filter.toSearchContainerFilterDto()).toContainersPagination()
}