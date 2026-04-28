package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.warehouse

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Warehouse
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class WarehouseDto(
    @JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val code: String,
    @JsonProperty(JsonFieldsProvider.WAREHOUSE_DESC) val description: String
)

fun Warehouse.toWarehouseDto() = WarehouseDto(
    code = code,
    description = description
)


