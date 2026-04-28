package ru.bz.bzinforintegrationservice.domain.model.mechanicalpart

import java.time.LocalDateTime

data class ReportedOperation(
    val projectCode: String,
    val elementCode: String,
    val rootItemCode: String,
    val parentItemCode: String,
    val parentItemPosition: String,
    val parentOperationCode: Int,
    val childOperationCode: Int,
    val reportedQuantity: Int,
    val userLogin: String,
    val reportedDateTime: LocalDateTime
)
