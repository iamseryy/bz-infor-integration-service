package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.inbound

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.applications.usecases.barcode.BarCodeUseCases
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.barcode.BarCodeDataMessage
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.barcode.toBarCodeDataDto

@Service
class BarCodeQueuesListener(
    private val barCodeUseCases: BarCodeUseCases
) {
    @RabbitListener(queues = ["\${application.rabbitmq.queue.find_barcode_data}"])
    fun onGetContainerByCode(code: String): BarCodeDataMessage =
        barCodeUseCases.findBarCodeJsonByCode(code)?.toBarCodeDataDto().let {
            BarCodeDataMessage(it)
        }

}