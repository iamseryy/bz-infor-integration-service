package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao

import org.springframework.jdbc.core.RowMapper
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.warehouse.WarehouseDto
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchBinDetailShotFilter
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Bin
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Warehouse
import java.sql.ResultSet

interface WarehouseDao {
    companion object {
        val warehouseDetailRowMapper = RowMapper<WarehouseDto> { resultSet: ResultSet, _: Int ->
            WarehouseDto(
                code = resultSet.getString(EntityFieldsProvider.WAREHOUSE_CODE),
                description = resultSet.getString(EntityFieldsProvider.WAREHOUSE_DESC)
            )
        }
    }

    fun getWarehouseDetail(warehouseCode: String): Warehouse?
    fun getBinDetailByShotFilter(filter: SearchBinDetailShotFilter): Bin?
}