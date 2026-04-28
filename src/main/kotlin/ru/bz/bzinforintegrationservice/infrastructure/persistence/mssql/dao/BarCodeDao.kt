package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao

interface BarCodeDao {
    fun findBarCodeDataByCode(code: String): String?
}