package ru.bz.bzinforintegrationservice.rabbit.listener

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.domain.usecases.warehouse.WarehouseUseCases
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.filter.SearchBinDetailShotFilterDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.filter.toSearchBinDetailShotFilter
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.warehouse.BinMessageDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.warehouse.WarehouseMessageDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.warehouse.toBinDto
import ru.bz.bzinforintegrationservice.presentation.rabbit.dto.warehouse.toWarehouseDto


@Service
class WarehouseQueuesListener(
    private val warehouseUseCases: WarehouseUseCases
) {
    @RabbitListener(queues = ["\${application.rabbitmq.queue.get_warehouse_detail}"])
    fun onGetWarehouseDetail(warehouseCode: String): WarehouseMessageDto =
        warehouseUseCases.getWarehouseDetail(warehouseCode)?.toWarehouseDto().let {warehouseDto ->
            WarehouseMessageDto(warehouseDto)
        }

    @RabbitListener(queues = ["\${application.rabbitmq.queue.get_location_detail}"])
    fun onGetLocationDetail(filter: SearchBinDetailShotFilterDto): BinMessageDto =
        warehouseUseCases.getLocationDetail(filter.toSearchBinDetailShotFilter())?.toBinDto().let { binDto ->
            BinMessageDto(binDto)
        }
}