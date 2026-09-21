package ru.bz.bzinforintegrationservice.applications.usecases.measuredremainder

import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.domain.repository.MeasuredRemainderRepository


@Service
class GetProjectCodesUseCase(
    private val repository: MeasuredRemainderRepository
) {
    operator fun invoke(): List<String> = repository.findProjectCodes()
}