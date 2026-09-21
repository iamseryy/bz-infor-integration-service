package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.RabbitMessage


@JsonInclude(JsonInclude.Include.ALWAYS)
class FindWarehouseCodesRequestMessage: RabbitMessage(messageType = "WAREHOUSE_CODES_SEARCH_REQUEST")