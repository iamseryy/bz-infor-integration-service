package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.location

import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Bin
import java.sql.ResultSet

data class BinDto(
    val code: String,
    val description: String,
    val warehouseCode: String
){
    companion object
}

fun BinDto.toBin() = Bin(
    code = code,
    description = description,
    warehouseCode = warehouseCode
)

fun BinDto.Companion.getInstance(rs: ResultSet) = BinDto(
    code = rs.getString(EntityFieldsProvider.BIN_CODE),
    description = rs.getString(EntityFieldsProvider.BIN_DESC),
    warehouseCode = rs.getString(EntityFieldsProvider.WAREHOUSE_CODE)
)
