package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.RabbitMessage
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.MeasuredRemainderDto
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.toMeasuredRemainderDimensions
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.toMeasuredRemainderLocation


@JsonInclude(JsonInclude.Include.ALWAYS)
data class MeasuredRemainderUpdateRequestMessage(
    @field:JsonProperty(JsonFieldsProvider.MEASURED_REMAINDER) val measuredRemainder: MeasuredRemainderDto,
    @field:JsonProperty(JsonFieldsProvider.USER_LOGIN) val userLogin: String
): RabbitMessage(messageType = "MEASURED_REMAINDER_UPDATE_REQUEST")


fun MeasuredRemainderUpdateRequestMessage.toMeasuredRemainder() = MeasuredRemainder(
    id = measuredRemainder.id,
    code = measuredRemainder.code,
    projectCode = measuredRemainder.projectCode,
    material = measuredRemainder.material,
    location = measuredRemainder.location.toMeasuredRemainderLocation(),
    status = measuredRemainder.status,
    comment = measuredRemainder.comment,
    dimensions = measuredRemainder.dimensions.toMeasuredRemainderDimensions(),
    inventoryDate = measuredRemainder.inventoryDate
)
