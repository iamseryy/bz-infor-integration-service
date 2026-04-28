package ru.bz.bzinforintegrationservice.presentation.rabbit.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import java.time.LocalDateTime
import java.util.*


@JsonInclude(JsonInclude.Include.ALWAYS)
abstract class RabbitMessage(
    @JsonProperty(JsonFieldsProvider.MESSAGE_ID) val messageId: String = UUID.randomUUID().toString(),

    @field:JsonFormat(shape = JsonFormat.Shape.STRING)
    @field:JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @field:JsonProperty(JsonFieldsProvider.DATE) val timestamp: LocalDateTime = LocalDateTime.now(),

    @JsonProperty(JsonFieldsProvider.TYPE) val messageType: String
) {
    constructor(messageType: String) : this (
        messageId = UUID.randomUUID().toString(),
        timestamp = LocalDateTime.now(),
        messageType = messageType
    )
}