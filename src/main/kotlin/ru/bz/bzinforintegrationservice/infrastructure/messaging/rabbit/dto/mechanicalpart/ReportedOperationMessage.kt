package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.mechanicalpart

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.RabbitMessage


@JsonInclude(JsonInclude.Include.ALWAYS)
class ReportedOperationMessage (
    @field:JsonProperty(JsonFieldsProvider.REPORTED_OPERATION) val reportedOperationDto: ReportedOperationDto
): RabbitMessage(messageType = "ReportedOperationMessage")