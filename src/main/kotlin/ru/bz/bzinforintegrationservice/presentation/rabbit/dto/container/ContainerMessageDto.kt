package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.container

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class ContainerMessageDto (
    @JsonProperty(JsonFieldsProvider.CONTAINER) val container: ContainerDto?
)