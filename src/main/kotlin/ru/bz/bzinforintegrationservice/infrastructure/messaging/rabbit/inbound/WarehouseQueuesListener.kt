package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.inbound

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.applications.usecases.warehouse.WarehouseUseCases
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.filter.SearchBinDetailShotFilterDto
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.filter.toSearchBinDetailShotFilter
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.location.BinMessageDto
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.location.WarehouseMessageDto
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.location.toBinDto
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.dto.location.toWarehouseDto

@Service
class WarehouseQueuesListener(
    private val warehouseUseCases: WarehouseUseCases
) {
    @RabbitListener(queues = ["\${application.rabbitmq.queue.get_warehouse_detail}"])
    fun onGetWarehouseDetail(warehouseCode: String): WarehouseMessageDto =
        warehouseUseCases.getWarehouseDetail(warehouseCode)?.toWarehouseDto().let { warehouseDto ->
            WarehouseMessageDto(warehouseDto)
        }

    @RabbitListener(queues = ["\${application.rabbitmq.queue.get_location_detail}"])
    fun onGetLocationDetail(filter: SearchBinDetailShotFilterDto): BinMessageDto =
        warehouseUseCases.getLocationDetail(filter.toSearchBinDetailShotFilter())?.toBinDto().let { binDto ->
            BinMessageDto(binDto)
        }
}