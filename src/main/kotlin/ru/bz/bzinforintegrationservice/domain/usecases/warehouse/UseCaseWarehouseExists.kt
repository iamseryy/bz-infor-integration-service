package ru.bz.bzinforintegrationservice.domain.usecases.warehouse

import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.domain.repository.WarehouseRepository


@Service
class UseCaseWarehouseExists(
    private val warehouseRepository: WarehouseRepository
) {
    operator fun invoke(warehouseCode: String): Boolean = warehouseRepository.warehouseExists(warehouseCode)
}