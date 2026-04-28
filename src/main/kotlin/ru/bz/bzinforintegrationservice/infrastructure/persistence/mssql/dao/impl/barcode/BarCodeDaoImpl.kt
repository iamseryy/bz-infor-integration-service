package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.barcode

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.BarCodeDao
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.barcode.mapper.BarCodeMappers
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.barcode.sqlprovider.BarCodeSqlProvider


@Component
class BarCodeDaoImpl(
    private val jdbcTemplate: JdbcTemplate,
    private val barCodeSqlProvider: BarCodeSqlProvider,
    private val barCodeMappers: BarCodeMappers
): BarCodeDao {
    override fun findBarCodeDataByCode(code: String): String? =
        jdbcTemplate.query(
            barCodeSqlProvider.findBarCodeDataByCode(code),
            barCodeMappers.findBarCodeDataByCodeMapper()
        ).firstOrNull()
}