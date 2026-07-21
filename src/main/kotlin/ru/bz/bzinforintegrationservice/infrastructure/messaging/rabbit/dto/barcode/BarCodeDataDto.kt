package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.barcode

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.barcode.BarCodeData
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class BarCodeDataDto(
    @JsonProperty(JsonFieldsProvider.DATA) val barCodeData: Map<String, Any>
)

fun BarCodeData.toBarCodeDataDto() = BarCodeDataDto(
    barCodeData = barCodeData
)