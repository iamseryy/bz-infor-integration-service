package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.RabbitMessage
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.location.LocationDto


@JsonInclude(JsonInclude.Include.ALWAYS)
data class LocationsResponseMessage(
    @field:JsonProperty(JsonFieldsProvider.LOCATIONS) val locations: List<LocationDto>,
): RabbitMessage(messageType = "LOCATIONS_SEARCH_RESPONSE")

