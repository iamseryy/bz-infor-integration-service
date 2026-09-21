package ru.bz.bzinforintegrationservice.applications.usecases.measuredremainder

import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.domain.common.Result
import ru.bz.bzinforintegrationservice.domain.common.Result.Failure
import ru.bz.bzinforintegrationservice.domain.common.Result.Success
import ru.bz.bzinforintegrationservice.domain.common.abortreason.AbortReason
import ru.bz.bzinforintegrationservice.domain.common.abortreason.Violation
import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.bzinforintegrationservice.domain.repository.MeasuredRemainderRepository


@Service
class UpdateMeasuredRemaindersUseCase(
    private val repository: MeasuredRemainderRepository
) {
    operator fun invoke(
        measuredRemainder: MeasuredRemainder,
        userLogin: String
    ): Result<MeasuredRemainder> = repository.update(measuredRemainder, userLogin).let { rowsCount ->
        if (rowsCount > 0) Success(measuredRemainder) else Failure(AbortReason(
            Violation("10000","update error")))
    }
}