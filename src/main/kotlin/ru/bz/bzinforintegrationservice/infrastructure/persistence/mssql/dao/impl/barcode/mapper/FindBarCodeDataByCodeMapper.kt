package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.barcode.mapper

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider



@Component
class FindBarCodeDataByCodeMapper(
    private val fieldsProvider: EntityFieldsProvider
) {
    operator fun invoke() = RowMapper<String> { rs, _ -> rs.getString(fieldsProvider.JSON_DATA) }
}