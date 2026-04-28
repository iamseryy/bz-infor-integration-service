package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.lot


import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.lot.LotDetail
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.item.ItemDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.item.toItemDto


@JsonInclude(JsonInclude.Include.ALWAYS)
data class LotDetailDto(
    @field:JsonProperty(JsonFieldsProvider.ITEM) val item: ItemDto,
    @field:JsonProperty(JsonFieldsProvider.LOT_CODE) val lotCode: String,
    @field:JsonProperty(JsonFieldsProvider.EFFECTIVITY_UNIT_CODE) val effectivityUnitCode: Long,
    @field:JsonProperty(JsonFieldsProvider.LOT_CODE_PARENT) val lotCodeParent: String,
    @field:JsonProperty(JsonFieldsProvider.LOT_CODE_SOURCE) val lotCodeSource: String,
)

fun LotDetail.toLotDetailDto() = LotDetailDto(
    item = item.toItemDto(),
    lotCode = lotCode,
    effectivityUnitCode = effectivityUnitCode,
    lotCodeParent = lotCodeParent,
    lotCodeSource = lotCodeSource
)
