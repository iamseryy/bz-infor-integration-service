package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.barcode.sqlprovider

import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider


private const val COMPANY_PROPERTY = "application.mssql.infor_company"

@Component
class BarCodeSqlProvider(
    environment: Environment,
    private val fieldsProvider: EntityFieldsProvider
){
    private val company = environment.getProperty(COMPANY_PROPERTY)

    fun findBarCodeDataByCode(code: String) = """
        SELECT zbbrc100.t_jstr AS ${fieldsProvider.JSON_DATA} 
        FROM tzbbrc100${company} AS zbbrc100 
        WHERE zbbrc100.t_idcd = N'${code}'
    """.trimIndent()
}