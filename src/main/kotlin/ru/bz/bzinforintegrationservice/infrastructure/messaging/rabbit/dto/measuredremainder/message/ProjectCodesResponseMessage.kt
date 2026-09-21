package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.RabbitMessage

@JsonInclude(JsonInclude.Include.ALWAYS)
data class ProjectCodesResponseMessage(
    @field:JsonProperty(JsonFieldsProvider.PROJECT_CODES) val projectCodes: List<String>,
): RabbitMessage(messageType = "PROJECT_CODES_SEARCH_RESPONSE")

