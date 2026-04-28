package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.filter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchMeasuredRemainderFilter
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.MeasuredRemainderStatus
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchMeasuredRemainderFilterDto(
    @field:JsonProperty(JsonFieldsProvider.ID) val id: String? = null,
    @field:JsonProperty(JsonFieldsProvider.REMAINDER_LIKE) val remainderLike: String? = null,
    @field:JsonProperty(JsonFieldsProvider.PROJECT_CODE) val projectCode: String? = null,
    @field:JsonProperty(JsonFieldsProvider.MATERIAL_LIKE) val materialLike: String? = null,
    @field:JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String? = null,
    @field:JsonProperty(JsonFieldsProvider.BIN_CODE) val binCode: String? = null,
    @field:JsonProperty(JsonFieldsProvider.STATUS) val status: Int? = null,
    @field:JsonProperty(JsonFieldsProvider.COMMENT) val comment: String? = null,
    @field:JsonProperty(JsonFieldsProvider.LENGTH_FROM) val lengthFrom: Double? = null,
    @field:JsonProperty(JsonFieldsProvider.WIDTH_FROM) val widthFrom: Double? = null,
    @field:JsonProperty(JsonFieldsProvider.DEPTH) val depth: Double? = null,
    @field:JsonProperty(JsonFieldsProvider.PAGE) val page: Int = 0,
    @field:JsonProperty(JsonFieldsProvider.PAGE_SIZE) val pageSize: Int = 10
)

fun SearchMeasuredRemainderFilterDto.toSearchMeasuredRemainderFilter() = SearchMeasuredRemainderFilter(
    id = id,
    remainderLike = remainderLike,
    projectCode = projectCode,
    materialLike = materialLike,
    warehouseCode = warehouseCode,
    binCode = binCode,
    status = status?.let { MeasuredRemainderStatus.getByNumber(it) },
    comment = comment,
    lengthFrom = lengthFrom,
    widthFrom = widthFrom,
    depth = depth,
    page = page,
    pageSize = pageSize
)