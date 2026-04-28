package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao

import org.springframework.jdbc.core.RowMapper
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.measuredremainder.MeasuredRemainderDto
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchMeasuredRemainderFilter
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.MeasuredRemaindersPagination
import java.sql.ResultSet


interface MeasuredRemainderDao {
    companion object {
        val rowMapper = RowMapper<MeasuredRemainderDto> { resultSet: ResultSet, _: Int ->
            MeasuredRemainderDto(
                id = resultSet.getString(EntityFieldsProvider.ID),
                remainder = resultSet.getString(EntityFieldsProvider.REMAINDER),
                projectCode = resultSet.getString(EntityFieldsProvider.PROJECT),
                material = resultSet.getString(EntityFieldsProvider.MATERIAL),
                warehouseCode = resultSet.getString(EntityFieldsProvider.WAREHOUSE),
                binCode = resultSet.getString(EntityFieldsProvider.LOCATION),
                sequence = resultSet.getInt(EntityFieldsProvider.SEQUENCE),
                statusCode = resultSet.getInt(EntityFieldsProvider.STATUS),
                comment = resultSet.getString(EntityFieldsProvider.COMMENT),
                length = resultSet.getDouble(EntityFieldsProvider.LENGTH),
                width = resultSet.getDouble(EntityFieldsProvider.WIDTH),
                depth = resultSet.getDouble(EntityFieldsProvider.DEPTH)
            )
        }
    }

    fun findByFilter(filter: SearchMeasuredRemainderFilter): MeasuredRemaindersPagination
}