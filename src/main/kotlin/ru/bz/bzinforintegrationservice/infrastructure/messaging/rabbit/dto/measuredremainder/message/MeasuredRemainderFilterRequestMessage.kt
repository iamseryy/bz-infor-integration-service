package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.RabbitMessage
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.common.page.DomainPageRequestDto
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.filter.MeasuredRemainderFilterDto

@JsonInclude(JsonInclude.Include.ALWAYS)
data class MeasuredRemainderFilterRequestMessage(
    @field:JsonProperty(JsonFieldsProvider.MEASURED_REMAINDER_FILTER) val filter: MeasuredRemainderFilterDto,
    @field:JsonProperty(JsonFieldsProvider.PAGE_REQUEST) val pageRequest: DomainPageRequestDto
): RabbitMessage(messageType = "MEASURED_REMAINDER_SEARCH_REQUEST")

