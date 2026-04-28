package ru.bz.bzinforintegrationservice.presentation.rabbit.listener

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.domain.usecases.barcode.BarCodeUseCases
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.barcode.BarCodeDataMessage
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.barcode.toBarCodeDataDto


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