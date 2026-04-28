package ru.bz.bzinforintegrationservice.domain.usecases.inventory

import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.domain.repository.InventoryRepository
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchInventoryBalanceFilter


@Component
class UseCaseFindInventoryBalanceByFilter(
    private val inventoryRepository: InventoryRepository
) {
    operator fun invoke(filter: SearchInventoryBalanceFilter) = inventoryRepository.findInventoryBalanceByFilter(filter)
}