package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.common.abortreason.AbortReason
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.RabbitMessage
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.common.abortreason.AbortReasonDto
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.MeasuredRemainderDto


@JsonInclude(JsonInclude.Include.NON_NULL)
data class MeasuredRemainderUpdateResponseMessage(
    @field:JsonProperty(JsonFieldsProvider.MEASURED_REMAINDER) val measuredRemainderDto: MeasuredRemainderDto,
    @field:JsonProperty(JsonFieldsProvider.ABORT_REASON) val abortReason: AbortReasonDto? = null
): RabbitMessage(messageType = "MEASURED_REMAINDER_UPDATE_RESPONSE")