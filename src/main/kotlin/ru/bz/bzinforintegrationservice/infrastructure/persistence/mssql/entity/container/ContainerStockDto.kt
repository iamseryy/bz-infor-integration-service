package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.container

import ru.bz.bzinforintegrationservice.domain.model.container.ContainerStock

data class ContainerStockDto(
    val containerStock: List<ContainerStockPositionDto>
)

fun ContainerStockDto.toContainerStock() = ContainerStock(
    containerStock = containerStock.map { it.toContainerStockPosition() }
)