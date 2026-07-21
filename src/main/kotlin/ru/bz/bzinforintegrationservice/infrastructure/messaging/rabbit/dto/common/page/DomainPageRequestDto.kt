package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.common.page

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.common.pagination.DomainPageRequest
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class DomainPageRequestDto(
    @JsonProperty(JsonFieldsProvider.NUMBER) val number: Int,
    @JsonProperty(JsonFieldsProvider.SIZE) val size: Int
) {
    val offset: Int get() = number * size
}

fun DomainPageRequestDto.toDomainPageRequest() = DomainPageRequest(
    number = number,
    size = size
)