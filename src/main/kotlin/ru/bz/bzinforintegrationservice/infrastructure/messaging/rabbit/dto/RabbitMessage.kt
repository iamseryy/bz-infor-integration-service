package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDateTime
import java.util.UUID


@JsonInclude(JsonInclude.Include.ALWAYS)
abstract class RabbitMessage(
    @field:JsonProperty(JsonFieldsProvider.MESSAGE_ID)
    override  val messageId: String = UUID.randomUUID().toString(),

    @field:JsonFormat(shape = JsonFormat.Shape.STRING)
    @field:JsonSerialize(using = LocalDateTimeSerializer::class)
    @field:JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @field:JsonProperty(JsonFieldsProvider.DATE) val timestamp: LocalDateTime = LocalDateTime.now(),

    @field:JsonProperty(JsonFieldsProvider.TYPE) val messageType: String
): MessageWithId