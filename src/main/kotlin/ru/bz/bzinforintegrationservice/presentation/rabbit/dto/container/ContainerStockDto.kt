package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.container

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.container.ContainerStock
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class ContainerStockDto(
    @JsonProperty(JsonFieldsProvider.STOCK) val containerStock: List<ContainerStockPositionDto>
)

fun ContainerStock.toContainerStockDto() = ContainerStockDto(
    containerStock = containerStock.map {it.toContainerStockPositionDto()}
)
