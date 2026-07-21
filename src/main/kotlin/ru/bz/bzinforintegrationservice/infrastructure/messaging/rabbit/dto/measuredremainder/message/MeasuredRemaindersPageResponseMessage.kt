package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.common.pagination.DomainPage
import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.RabbitMessage
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.common.page.PageMetaDataDto
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.MeasuredRemainderDto
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.toMeasuredRemainderDto


@JsonInclude(JsonInclude.Include.ALWAYS)
data class MeasuredRemaindersPageResponseMessage(
    @field:JsonProperty(JsonFieldsProvider.MEASURED_REMAINDERS) val measuredRemainders: List<MeasuredRemainderDto>,
    @field:JsonProperty(JsonFieldsProvider.PAGE_META_DATA) val page: PageMetaDataDto

): RabbitMessage(messageType = "MEASURED_REMAINDER_SEARCH_RESPONSE")

fun DomainPage<MeasuredRemainder>.toMeasuredRemaindersPageResponseMessage() = MeasuredRemaindersPageResponseMessage(
    measuredRemainders = content.map { it.toMeasuredRemainderDto() },
    page = PageMetaDataDto(
        number = number,
        size = size,
        totalElements = totalElements
    )
)