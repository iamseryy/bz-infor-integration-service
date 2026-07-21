package ru.bz.bzinforintegrationservice.applications.usecases.mechanicalpart

import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.domain.model.mechanicalpart.ReportedOperation
import ru.bz.bzinforintegrationservice.domain.repository.MechanicalpartRepoitory

@Component
class ReportOperationUseCase(
    private val mechanicalpartRepoitory: MechanicalpartRepoitory
) {
    operator fun invoke(reportedOperation: ReportedOperation): ReportedOperation? =
        if (mechanicalpartRepoitory.reportOperation(reportedOperation)) reportedOperation else null
}