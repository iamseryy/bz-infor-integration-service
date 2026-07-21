package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.common.abortreason

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.common.abortreason.Violation
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.NON_NULL)
data class ViolationDto(
    @field:JsonProperty(JsonFieldsProvider.CODE) val code: String?,
    @field:JsonProperty(JsonFieldsProvider.DESCRIPTION) val description: String?
)

fun Violation.toViolationDto() = ViolationDto(
    code = code,
    description = description
)
