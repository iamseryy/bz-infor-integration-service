package ru.bz.bzinforintegrationservice.domain.repository

import ru.bz.bzinforintegrationservice.domain.model.mechanicalpart.ReportedOperation

interface MechanicalpartRepoitory {
    fun reportOperation(reportedOperation: ReportedOperation): Boolean
}