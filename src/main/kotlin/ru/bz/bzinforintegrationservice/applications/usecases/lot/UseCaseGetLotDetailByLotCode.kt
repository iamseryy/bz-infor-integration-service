package ru.bz.bzinforintegrationservice.applications.usecases.lot

import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.domain.repository.LotRepository
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchLotDetailShotFilter
import ru.bz.bzinforintegrationservice.domain.model.lot.LotDetail


@Service
class UseCaseGetLotDetailByLotCode(
    private val lotRepository: LotRepository
) {
    operator fun invoke(filter: SearchLotDetailShotFilter): LotDetail? = lotRepository.getLotDetail(filter)
}