package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao

import org.springframework.jdbc.core.RowMapper
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item.ItemDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item.UnitDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.lot.LotDetailDto
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchLotDetailShotFilter
import ru.bz.bzinforintegrationservice.domain.model.lot.LotDetail
import java.sql.ResultSet

interface LotDao {

    companion object {
        val lotDetailRowMapper = RowMapper<LotDetailDto> { resultSet: ResultSet, _: Int ->
            LotDetailDto(
                item = ItemDto(
                    code = resultSet.getString(EntityFieldsProvider.ITEM_CODE).trim(),
                    description = resultSet.getString(EntityFieldsProvider.ITEM_DESC),
                    unit = UnitDto(resultSet.getString(EntityFieldsProvider.UNIT_CODE), resultSet.getString(EntityFieldsProvider.UNIT_DESC))
                ),
                lotCode = resultSet.getString(EntityFieldsProvider.LOT_CODE),
                effectivityUnitCode = resultSet.getLong(EntityFieldsProvider.EFFECTIVITY_UNIT_CODE),
                lotCodeParent = resultSet.getString(EntityFieldsProvider.LOT_CODE_PARENT),
                lotCodeSource = resultSet.getString(EntityFieldsProvider.LOT_CODE_SOURCE)
            )
        }
    }

    fun getLotDetail(filter: SearchLotDetailShotFilter): LotDetail?
}