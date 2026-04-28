package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.MeasuredRemaindersPagination
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider

@JsonInclude(JsonInclude.Include.ALWAYS)
data class MeasuredRemaindersPaginationDto(
    @field:JsonProperty(JsonFieldsProvider.MEASURED_REMAINDERS) val measuredRemainders: List<MeasuredRemainderDto>,
    @field:JsonProperty(JsonFieldsProvider.PAGE)  val page: Int,
    @field:JsonProperty(JsonFieldsProvider.PAGE_SIZE) val pageSize: Int
)

fun MeasuredRemaindersPagination.toMeasuredRemaindersPaginationDto() = MeasuredRemaindersPaginationDto(
    measuredRemainders = measuredRemainders.map { it.toMeasuredRemainderDto() },
    page = page,
    pageSize = pageSize
)
