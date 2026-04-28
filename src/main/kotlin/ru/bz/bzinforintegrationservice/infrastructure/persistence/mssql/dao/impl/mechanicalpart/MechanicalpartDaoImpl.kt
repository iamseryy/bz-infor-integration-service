package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.mechanicalpart

import mu.KotlinLogging
import org.springframework.core.env.Environment
import org.springframework.jdbc.UncategorizedSQLException
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.MechanicalpartDao
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.mechanicalpart.sqlprovider.MechanicalpartSqlProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.mechanicalpart.ReportedOperationEntity


@Component
class MechanicalpartDaoImpl(
    private val sqlProvider: MechanicalpartSqlProvider,
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate,
    private val environment: Environment
) : MechanicalpartDao {
    private val logger = KotlinLogging.logger {}

    override fun reportOperation(reportedOperation: ReportedOperationEntity): Int {
        val params = BeanPropertySqlParameterSource(reportedOperation)
        val sql = sqlProvider.setReportQuantityNamedSql()

        logger.debug { "Executing SQL: $sql" }
        logger.debug { "With parameters: ${params.parameterNames.joinToString()}" }
        logger.debug { "With parameter values: ${reportedOperation}" }

        try {
            val result = namedParameterJdbcTemplate.update(sql, params)
            logger.info { "Successfully inserted $result row(s) into tzbppc700${environment.getProperty("application.infor_company")} with parameter values: ${reportedOperation}" }
            return result
        } catch (e: UncategorizedSQLException) {
            logger.error(e) { "SQL error during insert operation for user: ${reportedOperation.userLogin}" }
            throw e
        }
    }
}