package ru.bz.bzinforintegrationservice.presentation.rabbit.listener

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.domain.usecases.inventory.InventoryUseCases
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.filter.SearchInventoryBalanceFilterDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.filter.toSearchInventoryBalanceFilter
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.stock.StockListPaginationDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.stock.toStockListPaginationDto


@Service
class InventoryQueuesListener(
    private val inventoryUseCases: InventoryUseCases
) {
    @RabbitListener(queues = ["\${application.rabbitmq.queue.find_stock_list}"])
    fun onFindInventoryBalanceByFilter(filter: SearchInventoryBalanceFilterDto): StockListPaginationDto =
        filter.toSearchInventoryBalanceFilter().let {
            inventoryUseCases.findInventoryBalanceByFilter(it)
        }.toStockListPaginationDto()
}