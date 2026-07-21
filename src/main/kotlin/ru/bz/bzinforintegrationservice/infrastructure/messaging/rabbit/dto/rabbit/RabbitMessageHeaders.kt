package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.rabbit

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import java.time.LocalDateTime
import java.util.UUID


data class RabbitMessageHeaders(
    @JsonProperty(JsonFieldsProvider.MESSAGE_ID)
    val messageId: String = UUID.randomUUID().toString(),

    @JsonProperty(JsonFieldsProvider.DATE)
    @field:JsonFormat(shape = JsonFormat.Shape.STRING)
    @field:JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val timestamp: LocalDateTime = LocalDateTime.now(),


    @JsonProperty(JsonFieldsProvider.TYPE)
    val messageType: String
)