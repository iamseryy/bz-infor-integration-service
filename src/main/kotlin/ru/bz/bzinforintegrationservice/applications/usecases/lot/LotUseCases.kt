package ru.bz.bzinforintegrationservice.applications.usecases.lot

import org.springframework.stereotype.Service


@Service
data class LotUseCases(
    val getLotDetailByLotCode: UseCaseGetLotDetailByLotCode
)
