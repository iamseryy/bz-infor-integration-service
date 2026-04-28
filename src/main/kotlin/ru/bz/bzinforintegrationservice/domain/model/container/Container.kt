package ru.bz.bzinforintegrationservice.domain.model.container

import ru.bz.bzinforintegrationservice.domain.model.warehouse.Location
import java.time.LocalDateTime

data class Container(
    val code: String,
    val warehouseOwnerCode: String,
    val description: String?,
    val status: ContainerStatus,
    val location: Location,
    val stock: ContainerStock,
    val userLoginLastModified: String,
    val dateLastModified: LocalDateTime
)
