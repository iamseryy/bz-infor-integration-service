package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.container

import ru.bz.bzinforintegrationservice.domain.model.container.ContainersPagination

data class ContainersPaginationDto(
    val containers: List<ContainerDto>,
    val page: Int,
    val pageSize: Int
)

fun ContainersPaginationDto.toContainersPagination() = ContainersPagination(
    containers = containers.map { it.toContainer() },
    page = page,
    pageSize = pageSize
)