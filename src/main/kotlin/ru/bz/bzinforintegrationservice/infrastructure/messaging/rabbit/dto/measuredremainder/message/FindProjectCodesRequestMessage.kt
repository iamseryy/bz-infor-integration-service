package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.RabbitMessage


@JsonInclude(JsonInclude.Include.ALWAYS)
class FindProjectCodesRequestMessage: RabbitMessage(messageType = "PROJECT_CODES_SEARCH_REQUEST")