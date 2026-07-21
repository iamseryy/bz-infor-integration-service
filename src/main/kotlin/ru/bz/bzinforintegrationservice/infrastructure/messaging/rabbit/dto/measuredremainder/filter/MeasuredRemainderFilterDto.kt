package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.filter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.filter.MeasuredRemainderFilter
import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainderStatus
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.NON_NULL)
data class MeasuredRemainderFilterDto(
    @field:JsonProperty(JsonFieldsProvider.ID) val id: String? = null,
    @field:JsonProperty(JsonFieldsProvider.CODE_LIKE) val codeLike: String? = null,
    @field:JsonProperty(JsonFieldsProvider.PROJECT_CODE) val projectCode: String? = null,
    @field:JsonProperty(JsonFieldsProvider.MATERIAL_LIKE) val materialLike: String? = null,
    @field:JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String? = null,
    @field:JsonProperty(JsonFieldsProvider.BIN_CODE) val binCode: String? = null,
    @field:JsonProperty(JsonFieldsProvider.STATUS) val status: Int? = null,
    @field:JsonProperty(JsonFieldsProvider.LENGTH_FROM) val lengthFrom: Double? = null,
    @field:JsonProperty(JsonFieldsProvider.WIDTH_FROM) val widthFrom: Double? = null,
    @field:JsonProperty(JsonFieldsProvider.THICKNESS_FROM) val thicknessFrom: Double? = null
)

fun MeasuredRemainderFilterDto.toMeasuredRemainderFilter() = MeasuredRemainderFilter(
    id = id,
    codeLike = codeLike,
    projectCode = projectCode,
    materialLike = materialLike,
    warehouseCode = warehouseCode,
    binCode = binCode,
    status = status?.let { MeasuredRemainderStatus.fromNumber(it) },
    lengthFrom = lengthFrom,
    widthFrom = widthFrom,
    thicknessFrom = thicknessFrom
)