package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.barcode

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.RabbitMessage


@JsonInclude(JsonInclude.Include.ALWAYS)
data class BarCodeDataMessage(
    @JsonProperty(JsonFieldsProvider.OBJECT_DATA) val barCodeData: BarCodeDataDto?
): RabbitMessage(messageType = "BarCodeDataMessage")


