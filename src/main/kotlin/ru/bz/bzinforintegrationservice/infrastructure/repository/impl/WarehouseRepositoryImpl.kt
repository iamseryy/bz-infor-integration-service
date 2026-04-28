package ru.bz.bzinforintegrationservice.infrastructure.repository.impl

import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.WarehouseDao
import ru.bz.bzinforintegrationservice.domain.repository.WarehouseRepository
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchBinDetailShotFilter
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Bin
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Warehouse


@Service
class WarehouseRepositoryImpl(
    private val warehouseDao: WarehouseDao
): WarehouseRepository {
    override fun getWarehouseDetail(warehouseCode: String): Warehouse? = warehouseDao.getWarehouseDetail(warehouseCode)

    override fun getLocationDetail(filter: SearchBinDetailShotFilter): Bin? = warehouseDao.getBinDetailByShotFilter(filter)

    override fun warehouseExists(warehouseCode: String): Boolean = warehouseDao.getWarehouseDetail(warehouseCode) != null

    override fun locationExists(filter: SearchBinDetailShotFilter): Boolean = warehouseDao.getBinDetailByShotFilter(filter) != null
}