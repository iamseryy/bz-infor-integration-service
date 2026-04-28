package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao

import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.container.ContainerDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.container.ContainersPaginationDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.filter.SearchContainerFilterDto


interface ContainerDao {
    fun getContainerDetailByCode(code: String): ContainerDto?
    fun findContainersDetailByFilter(filter: SearchContainerFilterDto): ContainersPaginationDto
}