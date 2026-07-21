package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.common.page

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class PageMetaDataDto(
    @JsonProperty(JsonFieldsProvider.NUMBER) val number: Int,
    @JsonProperty(JsonFieldsProvider.SIZE) val size: Int,
    @JsonProperty(JsonFieldsProvider.TOTAL_ELEMENTS) val totalElements: Long
)



