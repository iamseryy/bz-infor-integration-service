package ru.bz.bzinforintegrationservice.domain.model.container

data class ContainersPagination(
    val containers: List<Container>,
    val page: Int,
    val pageSize: Int
)
