package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.container.extractor

import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item.ItemDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item.UnitDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.order.WarehouseOrderDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.location.LocationDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.container.ContainerDetailStockPositionDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.container.ContainerDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.container.ContainerStockDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.container.ContainerStockPositionDto
import java.time.LocalDateTime


@Component
class ContainersExtractor(
    private val fieldsProvider: EntityFieldsProvider
) {
    operator fun invoke() = ResultSetExtractor<List<ContainerDto>> { rs ->
        ArrayList<ContainerDetailStockPositionDto>().let { containers ->
            while (rs.next()) {
                containers.add(
                    ContainerDetailStockPositionDto(
                        code = rs.getString(fieldsProvider.CODE),
                        warehouseOwnerCode = rs.getString(fieldsProvider.WAREHOUSE_OWNER_CODE),
                        description = rs.getString(fieldsProvider.DESCRIPTION),
                        statusCode = rs.getInt(fieldsProvider.STATUS_CODE),
                        warehouseCode = rs.getString(fieldsProvider.CONTAINER_WAREHOUSE_CODE),
                        binCode = rs.getString(fieldsProvider.CONTAINER_BIN_CODE),
                        userLoginLastModified = rs.getString(fieldsProvider.USER_LOGIN_LAST_MODIFIED),
                        dateLastModified = rs.getTimestamp(fieldsProvider.DATE_LAST_MODIFIED)?.toLocalDateTime(),
                        stockLine = rs.getInt(fieldsProvider.LINE),
                        warehouseOrderOrigin = rs.getInt(fieldsProvider.WAREHOUSE_ORDER_ORIGIN),
                        warehouseOrderCode = rs.getString(fieldsProvider.WAREHOUSE_ORDER_CODE),
                        warehouseOrderSet = rs.getInt(fieldsProvider.WAREHOUSE_ORDER_SET),
                        warehouseOrderLine = rs.getInt(fieldsProvider.WAREHOUSE_ORDER_LINE),
                        warehouseOrderSequence = rs.getInt(fieldsProvider.WAREHOUSE_ORDER_SEQUENCE),
                        itemCode = rs.getString(fieldsProvider.ITEM_CODE),
                        itemDescription = rs.getString(fieldsProvider.ITEM_DESC),
                        unitCode = rs.getString(fieldsProvider.UNIT_CODE),
                        unitDescription = rs.getString(fieldsProvider.UNIT_DESC),
                        lot = rs.getString(fieldsProvider.LOT),
                        quantity = rs.getDouble(fieldsProvider.QUANTITY),
                        stockWarehouseCode = rs.getString(fieldsProvider.WAREHOUSE_CODE),
                        stockBinCode = rs.getString(fieldsProvider.BIN_CODE)
                    )
                )
            }
            containers
        }.let { containers ->
            containers
                .groupBy { it.code }
                .map { (code, detailStockPositions) ->
                    ContainerDto(
                        code = code,
                        warehouseOwnerCode = detailStockPositions.firstOrNull()?.warehouseOwnerCode ?: "",
                        description = detailStockPositions.firstOrNull { it.description != null }?.description,
                        statusCode = detailStockPositions.firstOrNull()?.statusCode ?: 0,
                        location = LocationDto(
                            warehouseCode = detailStockPositions.firstOrNull()?.warehouseCode ?: "",
                            binCode = detailStockPositions.firstOrNull()?.binCode ?: ""
                        ),
                        userLoginLastModified = detailStockPositions.firstOrNull()?.userLoginLastModified ?: "",
                        dateLastModified = detailStockPositions.firstOrNull()?.dateLastModified ?: LocalDateTime.MIN,
                        stock = ContainerStockDto(
                            containerStock = detailStockPositions.mapNotNull { position ->
                                if (position.stockLine != 0) {
                                    ContainerStockPositionDto(
                                        line = position.stockLine ?: 0,
                                        warehouseOrder = WarehouseOrderDto(
                                            origin = position.warehouseOrderOrigin ?: 0,
                                            code = position.warehouseOrderCode ?: "",
                                            set = position.warehouseOrderSet ?: 0,
                                            line = position.warehouseOrderLine ?: 0,
                                            sequence = position.warehouseOrderSequence ?: 0,
                                        ),
                                        item = ItemDto(
                                            code = position.itemCode ?: "",
                                            description = position.itemDescription ?: "",
                                            unit = UnitDto(
                                                code = position.unitCode ?: "",
                                                description = position.unitDescription ?: ""
                                            )
                                        ),
                                        lotCode = position.lot ?: "",
                                        quantity = position.quantity ?: 0.0,
                                        location = LocationDto(
                                            warehouseCode = position.stockWarehouseCode ?: "",
                                            binCode = position.stockBinCode ?: ""
                                        )
                                    )
                                } else {
                                    null
                                }
                            }
                        )
                    )
                }
        }
    }
}
