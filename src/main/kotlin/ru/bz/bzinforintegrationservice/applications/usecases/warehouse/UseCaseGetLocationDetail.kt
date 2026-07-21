package ru.bz.bzinforintegrationservice.applications.usecases.warehouse

import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.domain.repository.WarehouseRepository
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchBinDetailShotFilter
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Bin


@Service
class UseCaseGetLocationDetail(
    private val warehouseRepository: WarehouseRepository
) {
    operator fun invoke(filter: SearchBinDetailShotFilter): Bin? = warehouseRepository.getLocationDetail(filter)
}