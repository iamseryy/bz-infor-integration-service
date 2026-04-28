package ru.bz.bzinforintegrationservice.domain.repository

import ru.bz.bzinforintegrationservice.domain.model.filter.SearchBinDetailShotFilter
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Bin
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Warehouse

interface WarehouseRepository {
    fun getWarehouseDetail(warehouseCode: String): Warehouse?
    fun getLocationDetail(filter: SearchBinDetailShotFilter): Bin?
    fun warehouseExists(warehouseCode: String): Boolean
    fun locationExists(filter: SearchBinDetailShotFilter): Boolean
}