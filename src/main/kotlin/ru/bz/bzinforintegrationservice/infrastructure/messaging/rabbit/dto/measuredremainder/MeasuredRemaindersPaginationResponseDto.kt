package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.RabbitMessage
import java.util.UUID


@JsonInclude(JsonInclude.Include.ALWAYS)
class MeasuredRemaindersPaginationResponseDto(
    @JsonProperty(JsonFieldsProvider.MEASURED_REMAINDERS) val measuredRemaindersPagination: MeasuredRemaindersPaginationDto,
    messageId: String = UUID.randomUUID().toString()
): RabbitMessage(messageId = messageId, messageType = "MEASURED_REMAINDERS_PAGINATION_RESPONSE")
