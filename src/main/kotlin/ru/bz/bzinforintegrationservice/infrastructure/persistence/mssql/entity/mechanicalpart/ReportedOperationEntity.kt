package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.mechanicalpart

import ru.bz.bzinforintegrationservice.domain.model.mechanicalpart.ReportedOperation
import java.time.LocalDateTime

data class ReportedOperationEntity(
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

fun ReportedOperation.toReportedOperationEntity() = ReportedOperationEntity(
    projectCode = projectCode,
    elementCode = elementCode,
    rootItemCode = "         $rootItemCode",
    parentItemCode = "         $parentItemCode",
    parentItemPosition = parentItemPosition,
    parentOperationCode = parentOperationCode,
    childOperationCode = childOperationCode,
    reportedQuantity = reportedQuantity,
    userLogin = userLogin,
    reportedDateTime = reportedDateTime
)
