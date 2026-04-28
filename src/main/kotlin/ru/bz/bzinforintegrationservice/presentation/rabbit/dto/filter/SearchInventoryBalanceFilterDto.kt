package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.filter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchInventoryBalanceFilter
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchInventoryBalanceFilterDto(
    @field:JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode:String?,
    @field:JsonProperty(JsonFieldsProvider.BIN_CODE) val binCode: String?,
    @field:JsonProperty(JsonFieldsProvider.ITEM_CODE) val itemCode: String?,
    @field:JsonProperty(JsonFieldsProvider.LOT_CODE) val lotCode: String?,
    @field:JsonProperty(JsonFieldsProvider.FIND_LOT_CHILDREN) val findLotChildren: Boolean,
    @field:JsonProperty(JsonFieldsProvider.PAGE) val page: Int,
    @field:JsonProperty(JsonFieldsProvider.PAGE_SIZE) val pageSize: Int
)

fun SearchInventoryBalanceFilterDto.toSearchInventoryBalanceFilter() = SearchInventoryBalanceFilter(
    warehouseCode = warehouseCode,
    binCode = binCode,
    itemCode = itemCode,
    lotCode = lotCode,
    findLotChildren = findLotChildren,
    page = page,
    pageSize = pageSize
)
