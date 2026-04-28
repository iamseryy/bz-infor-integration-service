package ru.bz.bzinforintegrationservice.domain.usecases.inventory

import org.springframework.stereotype.Component


@Component
data class InventoryUseCases(
    val findInventoryBalanceByFilter: UseCaseFindInventoryBalanceByFilter
)
