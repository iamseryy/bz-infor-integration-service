package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.common.abortreason

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.common.abortreason.AbortReason
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.NON_NULL)
data class AbortReasonDto(
    @field:JsonProperty(JsonFieldsProvider.GENERAL_VIOLATION) val generalViolation: ViolationDto?
)

fun AbortReason.toAbortReasonDto() = AbortReasonDto(
    generalViolation = generalViolation?.toViolationDto()
)
