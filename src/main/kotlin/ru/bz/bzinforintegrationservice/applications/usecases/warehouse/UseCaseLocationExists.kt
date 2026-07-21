package ru.bz.bzinforintegrationservice.applications.usecases.warehouse

import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.domain.repository.WarehouseRepository
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchBinDetailShotFilter


@Service
class UseCaseLocationExists (
    private val warehouseRepository: WarehouseRepository
){
    operator fun invoke(filter: SearchBinDetailShotFilter): Boolean = warehouseRepository.locationExists(filter)
}