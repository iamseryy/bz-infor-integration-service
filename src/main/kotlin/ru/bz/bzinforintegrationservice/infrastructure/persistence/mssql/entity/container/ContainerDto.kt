package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.container

import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.location.LocationDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.location.toLocation
import ru.bz.bzinforintegrationservice.domain.model.container.Container
import ru.bz.bzinforintegrationservice.domain.model.container.ContainerStatus
import java.time.LocalDateTime

data class ContainerDto(
    val code: String,
    val warehouseOwnerCode: String,
    val description: String?,
    val statusCode: Int,
    val location: LocationDto,
    val stock: ContainerStockDto,
    val userLoginLastModified: String,
    val dateLastModified: LocalDateTime
)

fun ContainerDto.toContainer() = Container(
    code = code,
    warehouseOwnerCode = warehouseOwnerCode,
    description = description,
    status = ContainerStatus.getByNumber(statusCode),
    location = location.toLocation(),
    stock = stock.toContainerStock(),
    userLoginLastModified = userLoginLastModified,
    dateLastModified = dateLastModified
)


