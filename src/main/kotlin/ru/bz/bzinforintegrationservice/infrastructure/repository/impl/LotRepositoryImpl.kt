package ru.bz.bzinforintegrationservice.infrastructure.repository.impl

import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.LotDao
import ru.bz.bzinforintegrationservice.domain.repository.LotRepository
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchLotDetailShotFilter
import ru.bz.bzinforintegrationservice.domain.model.lot.LotDetail

@Service
class LotRepositoryImpl(
    private val lotDao: LotDao
): LotRepository {
    override fun getLotDetail(filter: SearchLotDetailShotFilter): LotDetail? = lotDao.getLotDetail(filter)
}