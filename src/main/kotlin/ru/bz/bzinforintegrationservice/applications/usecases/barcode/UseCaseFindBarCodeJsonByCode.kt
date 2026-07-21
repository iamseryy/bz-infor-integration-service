package ru.bz.bzinforintegrationservice.applications.usecases.barcode

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.BarCodeDao
import ru.bz.bzinforintegrationservice.domain.model.barcode.BarCodeData


@Component
class UseCaseFindBarCodeJsonByCode(
    private val barCodeDao: BarCodeDao
) {
    operator fun invoke(code: String): BarCodeData? =
        barCodeDao.findBarCodeDataByCode(code)?.let {barcodeData ->
            jacksonObjectMapper().readValue<Map<String, Any>>(barcodeData)
        }?.let {barcodeDataMapperResult ->
            BarCodeData(barcodeDataMapperResult)
        }
}