package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.inbound

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.applications.usecases.item.ItemUseCases
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.item.ItemMessageDto
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.item.toItemDto

@Service
class ItemQueuesListener(
    private val itemUseCases: ItemUseCases
) {
    @RabbitListener(queues = ["\${application.rabbitmq.queue.get_item_detail}"])
    fun onGetItemDetail(itemCode: String): ItemMessageDto =
        itemUseCases.getItemDetailByItemCode(itemCode)?.toItemDto().let { itemDto ->
            ItemMessageDto(itemDto)
        }
}