package ru.bz.bzinforintegrationservice.domain.repository

import ru.bz.bzinforintegrationservice.domain.model.filter.SearchLotDetailShotFilter
import ru.bz.bzinforintegrationservice.domain.model.lot.LotDetail

interface LotRepository {
    fun getLotDetail(filter: SearchLotDetailShotFilter): LotDetail?
}