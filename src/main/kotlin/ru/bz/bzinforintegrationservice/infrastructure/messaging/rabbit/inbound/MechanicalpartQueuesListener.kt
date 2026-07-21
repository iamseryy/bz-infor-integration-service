package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.inbound

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.applications.usecases.mechanicalpart.MechanicalpartUseCases
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.mechanicalpart.ReportedOperationMessage
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.mechanicalpart.ReportedOperationResultMessage
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.mechanicalpart.toReportedOperation

@Component
class MechanicalpartQueuesListener (
    private val mechanicalpartUseCases: MechanicalpartUseCases
)  {
    @RabbitListener(queues = ["\${application.rabbitmq.queue.report_operation}"])
    fun onReportOperation(reportedOperationMessage: ReportedOperationMessage): ReportedOperationResultMessage =
        reportedOperationMessage.reportedOperationDto.toReportedOperation()
            .let { reportedOperation -> mechanicalpartUseCases.reportOperation(reportedOperation) }
            .let { result -> ReportedOperationResultMessage(result != null) }
            //.let { result -> ReportedOperationResultMessage(result?.toReportedOperationDto()) }
}