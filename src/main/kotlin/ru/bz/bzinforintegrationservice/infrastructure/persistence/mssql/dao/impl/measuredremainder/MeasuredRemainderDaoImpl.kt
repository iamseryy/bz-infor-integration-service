package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.measuredremainder

import mu.KotlinLogging
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.UncategorizedSQLException
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import ru.bz.bzinforintegrationservice.domain.common.pagination.DomainPage
import ru.bz.bzinforintegrationservice.domain.common.pagination.DomainPageRequest
import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.filter.MeasuredRemainderFilter
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.configuration.MssqlProperties
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.MeasuredRemainderDao
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.measuredremainder.sqlprovider.MeasuredRemainderSqlProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.measuredremainder.MeasuredRemainderDimensionsDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.measuredremainder.MeasuredRemainderDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.measuredremainder.MeasuredRemainderLocationDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.measuredremainder.toMeasuredRemainder
import java.sql.ResultSet
import java.time.LocalDateTime


@Repository
class MeasuredRemainderDaoImpl(
    private val props: MssqlProperties,
    private val sqlProvider: MeasuredRemainderSqlProvider,
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
): MeasuredRemainderDao {

    private val logger = KotlinLogging.logger {}

    private val rowMapperMeasuredRemainder = RowMapper<MeasuredRemainderDto> { rs: ResultSet, _: Int ->
        MeasuredRemainderDto(
            id = rs.getString(EntityFieldsProvider.ID),
            code = rs.getString(EntityFieldsProvider.CODE),
            projectCode = rs.getString(EntityFieldsProvider.PROJECT_CODE),
            material = rs.getString(EntityFieldsProvider.MATERIAL),
            location = MeasuredRemainderLocationDto(
                warehouseCode = rs.getString(EntityFieldsProvider.WAREHOUSE_CODE),
                binCode = rs.getString(EntityFieldsProvider.BIN_CODE),
                sequence = rs.getInt(EntityFieldsProvider.SEQUENCE)
            ),
            statusCode = rs.getInt(EntityFieldsProvider.STATUS),
            comment = rs.getString(EntityFieldsProvider.COMMENT),
            dimensions = MeasuredRemainderDimensionsDto(
                length = rs.getDouble(EntityFieldsProvider.LENGTH),
                width = rs.getDouble(EntityFieldsProvider.WIDTH),
                thickness = rs.getDouble(EntityFieldsProvider.THICKNESS)
            ),
            inventoryDate = rs.getTimestamp(EntityFieldsProvider.INVENTORY_DATE)?.toLocalDateTime() ?: LocalDateTime.MIN
        )
    }

    private val rowMapperBinCodes = RowMapper<List<String>> { rs: ResultSet, _: Int ->
        rs.getArray(EntityFieldsProvider.BIN_CODE) as List<String>
    }

    override fun findPage(
        filter: MeasuredRemainderFilter,
        pageRequest: DomainPageRequest
    ): DomainPage<MeasuredRemainder> {
        val (sql, params) = sqlProvider.buildFindQuery(filter)

        val countSql = "SELECT COUNT(*) FROM($sql) AS count_table"

        val offset = if (pageRequest.number > 0) pageRequest.number * pageRequest.size else 0

        val paginateSql = "$sql ORDER BY whwmd530.t_huid OFFSET :offset ROWS FETCH NEXT :size ROWS ONLY"
        val paginateParams = MapSqlParameterSource(params.values)
            .addValue("offset", offset)
            .addValue("size", pageRequest.size)

        logger.debug { "Executing SQL: $paginateSql" }
        logger.debug { "With parameters: ${paginateParams}" }

        return try {
            val totalElements = namedParameterJdbcTemplate.queryForObject(countSql, params, Long::class.java) ?: 0L
            val results = namedParameterJdbcTemplate.query(paginateSql, paginateParams, rowMapperMeasuredRemainder)

            DomainPage<MeasuredRemainder>(
                content = results.map { it.toMeasuredRemainder() },
                number = pageRequest.number,
                size = pageRequest.size,
                totalElements = totalElements
            )
        } catch (e: DataAccessException) {
            logger.error(e) { "Database error while searching remainders with filter: $filter" }
            throw e
        }
    }


    override fun update(
        measuredRemainder: MeasuredRemainder,
        userLogin: String
    ): Int {
        val (sql, params) = sqlProvider.buildUpdateQuery(measuredRemainder)

        logger.debug { "Executing SQL: $sql" }
        logger.debug { "With parameters: $params" }

        try {
            val result = namedParameterJdbcTemplate.update(sql, params)
            logger.info { "Successfully updated $result row(s) into twhwmd530${props.inforCompany} with parameter values: ${measuredRemainder}" }
            return result
        } catch (e: UncategorizedSQLException) {
            logger.error(e) { "SQL error during insert operation for user: ${userLogin}" }
            throw e
        }
    }

    override fun findBinCodesByWarehouseCode(warehouseCode: String): List<String> {
        val (sql, params) = sqlProvider.buildFindBinCodesByWarehouseCodeQuery(warehouseCode)
        logger.debug { "Executing SQL: $sql" }
        logger.debug { "With parameters: $params" }

        try {
            val result = namedParameterJdbcTemplate.query(sql, params, rowMapperBinCodes)
            logger.info { "Successfully updated $result row(s) into twhwmd530${props.inforCompany} with parameter values: ${warehouseCode}" }
            return result.
        } catch (e: UncategorizedSQLException) {
            logger.error(e) { "SQL error during insert operation for user: ${userLogin}" }
            throw e
        }
    }
}