package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao

import org.springframework.jdbc.core.RowMapper
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item.ItemDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item.UnitDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.stock.StockDto
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchInventoryBalanceFilter
import ru.bz.bzinforintegrationservice.domain.model.stock.StockListPagination
import java.sql.ResultSet

interface InventoryDao {
    companion object {
        val rowMapper = RowMapper<StockDto> { resultSet: ResultSet, _: Int ->
            StockDto(
                warehouseCode = resultSet.getString(EntityFieldsProvider.WAREHOUSE_CODE),
                binCode = resultSet.getString(EntityFieldsProvider.BIN_CODE),
                item = ItemDto(
                    code = resultSet.getString(EntityFieldsProvider.ITEM_CODE),
                    description = resultSet.getString(EntityFieldsProvider.ITEM_DESC),
                    unit = UnitDto(
                        code = resultSet.getString(EntityFieldsProvider.UNIT_CODE),
                        description = resultSet.getString(EntityFieldsProvider.UNIT_DESC)
                    )
                ),
                lotCode =  resultSet.getString(EntityFieldsProvider.LOT_CODE),
                quantityAvailable = resultSet.getDouble(EntityFieldsProvider.QUANTITY_AVAILABLE),
                quantityBlocked = resultSet.getDouble(EntityFieldsProvider.QUANTITY_BLOCKED),
                quantityAllocated = resultSet.getDouble(EntityFieldsProvider.QUANTITY_ALLOCATED)
            )
        }
    }

    fun findInventoryBalanceByFilter(filter: SearchInventoryBalanceFilter): StockListPagination
}