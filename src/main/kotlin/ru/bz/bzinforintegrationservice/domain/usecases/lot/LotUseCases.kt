package ru.bz.bzinforintegrationservice.domain.usecases.lot

import org.springframework.stereotype.Service


@Service
data class LotUseCases(
    val getLotDetailByLotCode: UseCaseGetLotDetailByLotCode
)
