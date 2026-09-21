package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.inbound

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.Message
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.applications.usecases.measuredremainder.MeasuredRemainderUseCases
import ru.bz.bzinforintegrationservice.domain.common.Result
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.common.abortreason.toAbortReasonDto
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.common.page.toDomainPageRequest
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.location.toLocationDto
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.filter.toMeasuredRemainderFilter
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.message.*
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.toMeasuredRemainder
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.measuredremainder.toMeasuredRemainderDto
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.interceptor.RabbitMdcInterceptor

@Component
class MeasuredRemainderRabbitListener(
    private val useCases: MeasuredRemainderUseCases
) {

    @RabbitListener(queues = ["\${application.rabbitmq.queue.find_measured_remainders}"])
    fun handleSearchMeasuredRemaindersRequest(
        message: MeasuredRemainderFilterRequestMessage,
        @Header(RabbitMdcInterceptor.TRACE_ID_HEADER) traceId: String
    ): Message<MeasuredRemaindersPageResponseMessage>  =
        useCases.search(
            filter = message.filter.toMeasuredRemainderFilter(),
            pageRequest = message.pageRequest.toDomainPageRequest()
        ).toMeasuredRemaindersPageResponseMessage()
            .let { responseMessage ->
                MessageBuilder.withPayload(responseMessage)
                    .setHeader(RabbitMdcInterceptor.TRACE_ID_HEADER, traceId)
                    .build()
            }

    @RabbitListener(queues = ["\${application.rabbitmq.queue.update_measured_remainder}"])
    fun handleMeasuredRemainderUpdateRequest(
        message: MeasuredRemainderUpdateRequestMessage,
        @Header(RabbitMdcInterceptor.TRACE_ID_HEADER) traceId: String
    ): Message<MeasuredRemainderUpdateResponseMessage>  =
        useCases.update(
            measuredRemainder = message.measuredRemainder.toMeasuredRemainder(),
            userLogin = message.userLogin
        ).let { result ->
            when (result) {
                is Result.Success -> MeasuredRemainderUpdateResponseMessage(
                        measuredRemainderDto = result.data.toMeasuredRemainderDto()
                    )
                is Result.Failure -> MeasuredRemainderUpdateResponseMessage(
                        measuredRemainderDto = message.measuredRemainder,
                        abortReason = result.abortReason.toAbortReasonDto()
                    )

            }
        }.let { responseMessage ->
                MessageBuilder.withPayload(responseMessage)
                    .setHeader(RabbitMdcInterceptor.TRACE_ID_HEADER, traceId)
                    .build()
            }


    @RabbitListener(queues = ["\${application.rabbitmq.queue.find_locations_of_measured_remainders}"])
    fun handleSearchLocationsRequest(
        message: FindLocationsByWarehouseCodeRequestMessage,
        @Header(RabbitMdcInterceptor.TRACE_ID_HEADER) traceId: String
    ): Message<LocationsResponseMessage>  =
        useCases.getLocationsByWarehouseCode(
            warehouseCode = message.warehouseCode
        ).let { locations ->
            LocationsResponseMessage(locations.map { it.toLocationDto() })
        }.let { responseMessage ->
                MessageBuilder.withPayload(responseMessage)
                    .setHeader(RabbitMdcInterceptor.TRACE_ID_HEADER, traceId)
                    .build()
            }

    @RabbitListener(queues = ["\${application.rabbitmq.queue.find_materials_of_measured_remainders}"])
    fun handleSearchMaterialsRequest(
        message: FindMaterialsRequestMessage,
        @Header(RabbitMdcInterceptor.TRACE_ID_HEADER) traceId: String
    ): Message<MaterialsResponseMessage>  =
        MaterialsResponseMessage(useCases.getMaterials()).let { responseMessage ->
            MessageBuilder.withPayload(responseMessage)
                .setHeader(RabbitMdcInterceptor.TRACE_ID_HEADER, traceId)
                .build()
        }


    @RabbitListener(queues = ["\${application.rabbitmq.queue.find_project_codes_of_measured_remainders}"])
    fun handleSearchProjectCodesRequest(
        message: FindProjectCodesRequestMessage,
        @Header(RabbitMdcInterceptor.TRACE_ID_HEADER) traceId: String
    ): Message<ProjectCodesResponseMessage>  =
        ProjectCodesResponseMessage(useCases.getProjectCodes()).let { responseMessage ->
            MessageBuilder.withPayload(responseMessage)
                .setHeader(RabbitMdcInterceptor.TRACE_ID_HEADER, traceId)
                .build()
        }


    @RabbitListener(queues = ["\${application.rabbitmq.queue.find_warehouse_codes_of_measured_remainders}"])
    fun handleSearchWarehouseCodesRequest(
        message: FindWarehouseCodesRequestMessage,
        @Header(RabbitMdcInterceptor.TRACE_ID_HEADER) traceId: String
    ): Message<WarehouseCodesResponseMessage>  =
        WarehouseCodesResponseMessage(useCases.getWarehouseCodes()).let { responseMessage ->
            MessageBuilder.withPayload(responseMessage)
                .setHeader(RabbitMdcInterceptor.TRACE_ID_HEADER, traceId)
                .build()
        }


//        {
//
//        val paginationDto = useCases.search(
//            filter = message.filter.toMeasuredRemainderFilter(),
//            pageRequest = message.pageRequest.toDomainPageRequest()
//        )
//
//        return MessageBuilder.withPayload(paginationDto.toMeasuredRemaindersPageResponseMessage())
//            .setHeader(RabbitMdcInterceptor.TRACE_ID_HEADER, traceId)
//            .build()
//    }
}