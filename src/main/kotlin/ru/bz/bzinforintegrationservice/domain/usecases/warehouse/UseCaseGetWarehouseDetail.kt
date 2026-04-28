package ru.bz.bzinforintegrationservice.domain.usecases.warehouse

import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.domain.repository.WarehouseRepository
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Warehouse


@Service
class UseCaseGetWarehouseDetail(
    private val warehouseRepository: WarehouseRepository
) {
    operator fun invoke(warehouseCode: String): Warehouse? = warehouseRepository.getWarehouseDetail(warehouseCode)
}