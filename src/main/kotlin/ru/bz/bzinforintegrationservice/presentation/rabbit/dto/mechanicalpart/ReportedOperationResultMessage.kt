package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.mechanicalpart

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.RabbitMessage


@JsonInclude(JsonInclude.Include.ALWAYS)
class ReportedOperationResultMessage (
    @JsonProperty(JsonFieldsProvider.REPORTED_OPERATION_RESULT) val reportedOperationDto: Boolean
) : RabbitMessage(messageType = "ReportedOperationResultMessage")