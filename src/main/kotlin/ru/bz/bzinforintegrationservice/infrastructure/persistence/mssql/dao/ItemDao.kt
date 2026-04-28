package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao

import org.springframework.jdbc.core.RowMapper
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item.ItemDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item.UnitDto
import ru.bz.bzinforintegrationservice.domain.model.Item.Item
import java.sql.ResultSet

interface ItemDao {
    companion object {
        val itemRowMapper = RowMapper<ItemDto> { resultSet: ResultSet, _: Int ->
            ItemDto(
                code = resultSet.getString(EntityFieldsProvider.ITEM_CODE).trim(),
                description = resultSet.getString(EntityFieldsProvider.ITEM_DESC),
                unit = UnitDto(resultSet.getString(EntityFieldsProvider.UNIT_CODE), resultSet.getString(EntityFieldsProvider.UNIT_DESC))
            )
        }
    }

    fun getItemDetail(itemCode: String): Item?
}