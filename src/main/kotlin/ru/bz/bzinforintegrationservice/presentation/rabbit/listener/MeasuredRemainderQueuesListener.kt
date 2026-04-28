package ru.bz.bzinforintegrationservice.presentation.rabbit.listener

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.domain.usecases.measuredRemainder.MeasuredRemainderUseCases
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.filter.SearchMeasuredRemainderFilterDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.filter.toSearchMeasuredRemainderFilter
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.measuredremainder.MeasuredRemaindersPaginationDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.measuredremainder.toMeasuredRemaindersPaginationDto


@Service
class MeasuredRemainderQueuesListener(
    private val measuredRemainderUseCases: MeasuredRemainderUseCases
) {
    @RabbitListener(queues = ["\${application.rabbitmq.queue.find_measured-remainders}"])
    fun onFindMeasuredRemainderByFilter(filter: SearchMeasuredRemainderFilterDto): MeasuredRemaindersPaginationDto =
        filter.toSearchMeasuredRemainderFilter().let {
            measuredRemainderUseCases.findByFilter(it)
        }.toMeasuredRemaindersPaginationDto()
}
