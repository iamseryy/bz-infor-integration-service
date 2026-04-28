package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.mechanicalpart

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.RabbitMessage


@JsonInclude(JsonInclude.Include.ALWAYS)
class ReportedOperationMessage (
    @field:JsonProperty(JsonFieldsProvider.REPORTED_OPERATION) val reportedOperationDto: ReportedOperationDto
): RabbitMessage(messageType = "ReportedOperationMessage")