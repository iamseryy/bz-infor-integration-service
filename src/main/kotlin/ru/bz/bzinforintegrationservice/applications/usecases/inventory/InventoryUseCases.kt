package ru.bz.bzinforintegrationservice.applications.usecases.inventory

import org.springframework.stereotype.Component


@Component
data class InventoryUseCases(
    val findInventoryBalanceByFilter: UseCaseFindInventoryBalanceByFilter
)
