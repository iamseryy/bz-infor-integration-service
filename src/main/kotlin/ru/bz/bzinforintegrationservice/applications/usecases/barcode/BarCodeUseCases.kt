package ru.bz.bzinforintegrationservice.applications.usecases.barcode

import org.springframework.stereotype.Component


@Component
data class BarCodeUseCases(
    val findBarCodeJsonByCode: UseCaseFindBarCodeJsonByCode
)
