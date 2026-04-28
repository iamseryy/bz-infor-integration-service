package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.barcode.mapper

import org.springframework.stereotype.Component

@Component
data class BarCodeMappers(
    val findBarCodeDataByCodeMapper: FindBarCodeDataByCodeMapper
)