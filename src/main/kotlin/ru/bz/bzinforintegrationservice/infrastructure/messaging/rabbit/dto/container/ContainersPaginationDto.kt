package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.container

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.container.ContainersPagination
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class ContainersPaginationDto(
    @JsonProperty(JsonFieldsProvider.CONTAINERS) val containers: List<ContainerDto>,
    @JsonProperty(JsonFieldsProvider.PAGE) val page: Int,
    @JsonProperty(JsonFieldsProvider.PAGE_SIZE) val pageSize: Int
)


fun ContainersPagination.toContainersPaginationDto() = ContainersPaginationDto(
    containers = containers.map { it.toContainerDto() },
    page = page,
    pageSize = pageSize
)
