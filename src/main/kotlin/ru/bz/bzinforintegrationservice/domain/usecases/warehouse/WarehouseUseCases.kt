package ru.bz.bzinforintegrationservice.domain.usecases.warehouse

import org.springframework.stereotype.Service

@Service
data class WarehouseUseCases(
    val getWarehouseDetail: UseCaseGetWarehouseDetail,
    val useCaseWarehouseExists: UseCaseWarehouseExists,
    val getLocationDetail: UseCaseGetLocationDetail,
    val locationExists: UseCaseLocationExists
)
