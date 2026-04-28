package ru.bz.bzinforintegrationservice.presentation.rabbit.listener

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.domain.usecases.container.ContainerUseCases
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.container.ContainerMessageDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.container.ContainersPaginationMessageDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.container.toContainerDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.container.toContainersPaginationDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.filter.SearchContainerFilterDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.filter.toSearchContainerFilter


@Service
class ContainerQueuesListener (
    private val containerUseCases: ContainerUseCases
){
    @RabbitListener(queues = ["\${application.rabbitmq.queue.get_container}"])
    fun onGetContainerByCode(code: String): ContainerMessageDto =
        containerUseCases.getContainerByCode(code)?.toContainerDto().let { containerDto ->
            ContainerMessageDto(containerDto)
        }

    @RabbitListener(queues = ["\${application.rabbitmq.queue.find_containers}"])
    fun onFindContainersByFilter(filterDto: SearchContainerFilterDto): ContainersPaginationMessageDto =
        filterDto.toSearchContainerFilter()
            .let { filter -> containerUseCases.findContainersByFilter(filter) }.toContainersPaginationDto()
            .let { containers -> ContainersPaginationMessageDto(containers) }
}