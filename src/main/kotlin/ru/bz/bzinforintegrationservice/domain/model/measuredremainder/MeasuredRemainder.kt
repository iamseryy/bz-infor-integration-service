package ru.bz.bzinforintegrationservice.domain.model.measuredremainder

data class MeasuredRemainder(
    val id: String,
    val remainder: String,
    val projectCode: String,
    val material: String,
    val warehouseCode: String,
    val binCode: String,
    val sequence: Int,
    val status: MeasuredRemainderStatus,
    val comment: String,
    val length: Double,
    val width: Double,
    val depth: Double
)
