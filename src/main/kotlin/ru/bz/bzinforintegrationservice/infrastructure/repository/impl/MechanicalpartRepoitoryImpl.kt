package ru.bz.bzinforintegrationservice.infrastructure.repository.impl

import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.domain.model.mechanicalpart.ReportedOperation
import ru.bz.bzinforintegrationservice.domain.repository.MechanicalpartRepoitory
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.MechanicalpartDao
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.mechanicalpart.toReportedOperationEntity


@Component
class MechanicalpartRepoitoryImpl(
    private val mechanicalpartDao: MechanicalpartDao
): MechanicalpartRepoitory {
    override fun reportOperation(reportedOperation: ReportedOperation): Boolean =
        mechanicalpartDao.reportOperation(reportedOperation.toReportedOperationEntity()) > 0
}