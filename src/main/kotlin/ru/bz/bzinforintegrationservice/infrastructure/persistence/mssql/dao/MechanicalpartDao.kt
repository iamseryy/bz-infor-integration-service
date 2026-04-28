package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao

import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.mechanicalpart.ReportedOperationEntity

interface MechanicalpartDao {
    fun reportOperation(reportedOperation: ReportedOperationEntity): Int
}