package ru.bz.bzinforintegrationservice.domain.usecases.barcode

import org.springframework.stereotype.Component


@Component
data class BarCodeUseCases(
    val findBarCodeJsonByCode: UseCaseFindBarCodeJsonByCode
)
