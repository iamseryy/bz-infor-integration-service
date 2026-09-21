package ru.bz.bzinforintegrationservice.applications.usecases.measuredremainder

import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Location
import ru.bz.bzinforintegrationservice.domain.repository.MeasuredRemainderRepository


@Service
class GetLocationsByWarehouseCodeUseCase(
    private val repository: MeasuredRemainderRepository
) {
    operator fun invoke(warehouseCode: String?): List<Location> = repository.findLocationsByWarehouseCode(warehouseCode)
}