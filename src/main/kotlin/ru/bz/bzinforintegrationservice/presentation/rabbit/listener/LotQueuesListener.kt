package ru.bz.bzinforintegrationservice.presentation.rabbit.listener

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.domain.usecases.lot.LotUseCases
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.filter.SearchLotDetailShotFilterDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.filter.toSearchLotDetailShotFilter
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.lot.LotDetailMessageDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.lot.toLotDetailDto


@Service
class LotQueuesListener (
    private val lotUseCases: LotUseCases
){
    @RabbitListener(queues = ["\${application.rabbitmq.queue.get_lot_detail}"])
    fun onGetItemDetail(filter: SearchLotDetailShotFilterDto): LotDetailMessageDto =
        lotUseCases.getLotDetailByLotCode(filter.toSearchLotDetailShotFilter())?.toLotDetailDto().let { lotDetailDto ->
            LotDetailMessageDto(lotDetailDto)
        }
}