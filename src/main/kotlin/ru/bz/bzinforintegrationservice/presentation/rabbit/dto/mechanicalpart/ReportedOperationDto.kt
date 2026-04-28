package ru.bz.bzinforintegrationservice.presentation.rabbit.dto.mechanicalpart

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import ru.bz.bzinforintegrationservice.domain.model.mechanicalpart.ReportedOperation
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.JsonFieldsProvider
import java.time.LocalDateTime


@JsonInclude(JsonInclude.Include.ALWAYS)
data class ReportedOperationDto(
    @JsonProperty(JsonFieldsProvider.PROJECT_CODE) val projectCode: String,
    @JsonProperty(JsonFieldsProvider.ELEMENT_CODE) val elementCode: String,
    @JsonProperty(JsonFieldsProvider.ROOT_ITEM_CODE) val rootItemCode: String,
    @JsonProperty(JsonFieldsProvider.PARENT_ITEM_CODE) val parentItemCode: String,
    @JsonProperty(JsonFieldsProvider.PARENT_ITEM_POSITION) val parentItemPosition: String,
    @JsonProperty(JsonFieldsProvider.PARENT_OPERATION_CODE) val parentOperationCode: Int,
    @JsonProperty(JsonFieldsProvider.CHILD_OPERATION_CODE) val childOperationCode: Int,
    @JsonProperty(JsonFieldsProvider.REPORTED_QUANTITY) val reportedQuantity: Int,
    @JsonProperty(JsonFieldsProvider.USER_LOGIN) val userLogin: String,

//    @JsonFormat(shape = JsonFormat.Shape.STRING)
//    @JsonSerialize(using = LocalDateTimeSerializer::class)
//    @JsonProperty(JsonFieldsProvider.REPORTED_DATE_TIME) val reportedDateTime: LocalDateTime
)

fun ReportedOperationDto.toReportedOperation(): ReportedOperation = ReportedOperation(
    projectCode = projectCode,
    elementCode = elementCode,
    rootItemCode = rootItemCode,
    parentItemCode = parentItemCode,
    parentItemPosition = parentItemPosition,
    parentOperationCode = parentOperationCode,
    childOperationCode = childOperationCode,
    reportedQuantity = reportedQuantity,
    userLogin = userLogin,
    reportedDateTime = LocalDateTime.now()
//    reportedDateTime = reportedDateTime
)

fun ReportedOperation.toReportedOperationDto(): ReportedOperationDto = ReportedOperationDto(
    projectCode = projectCode,
    elementCode = elementCode,
    rootItemCode = rootItemCode,
    parentItemCode = parentItemCode,
    parentItemPosition = parentItemPosition,
    parentOperationCode = parentOperationCode,
    childOperationCode = childOperationCode,
    reportedQuantity = reportedQuantity,
    userLogin = userLogin
)