package ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder

import ru.bz.baltic_shipyard_inventory_service.domain.model.dimensions.Dimensions
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Location
import java.time.LocalDateTime

data class MeasuredRemainder(
    var id: String,
    var code: String,
    val projectCode: String,
    var material: String,
    var location: MeasuredRemainderLocation,
    var status: MeasuredRemainderStatus,
    var comment: String,
    var dimensions: MeasuredRemainderDimensions,
    var inventoryDate: LocalDateTime
)