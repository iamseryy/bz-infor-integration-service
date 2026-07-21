package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.config

object RabbitKeys {
    const val MEASURED_REMAINDERS_EXCHANGE = "measured_remainders_direct"
    const val WAREHOUSE_EXCHANGE = "warehouse_direct"
    const val BARCODE_EXCHANGE = "barcode_direct"
    const val MECHANICAL_PART_EXCHANGE = "mechanicalpart_direct"

    const val FIND_MEASURED_REMAINDERS_QUEUE = "find_measured_remainders"
    const val UPDATE_MEASURED_REMAINDER_QUEUE = "update_measured_remainder"
    const val INVENTORY_MEASURED_REMAINDERS_QUEUE = "inventory_measured_remainders"
    const val FIND_STOCK_LIST_QUEUE = "find_stock_list"
    const val GET_ITEM_DETAIL_QUEUE = "get_item_detail"
    const val GET_LOT_DETAIL_QUEUE = "get_lot_detail"
    const val GET_WAREHOUSE_DETAIL_QUEUE = "get_warehouse_detail"
    const val GET_LOCATION_DETAIL_QUEUE = "get_location_detail"
    const val GET_CONTAINER_QUEUE = "get_container"
    const val FIND_CONTAINERS_QUEUE = "find_containers"
    const val FIND_BARCODE_DATA_QUEUE = "find_barcode_data"
    const val REPORT_OPERATION_QUEUE = "report_operation"

    const val FIND_MEASURED_REMAINDERS_KEY = "find_measured_remainders"
    const val UPDATE_MEASURED_REMAINDER_KEY = "update_measured_remainder"
    const val INVENTORY_MEASURED_REMAINDERS_KEY = "inventory_measured_remainders"
    const val FIND_STOCK_LIST_KEY = "find_stock_list"
    const val GET_ITEM_DETAIL_KEY = "get_item_detail"
    const val GET_LOT_DETAIL_KEY = "get_lot_detail"
    const val GET_WAREHOUSE_DETAIL_KEY = "get_warehouse_detail"
    const val GET_LOCATION_DETAIL_KEY = "get_location_detail"
    const val GET_CONTAINER_KEY = "get_container"
    const val FIND_CONTAINERS_KEY = "find_containers"
    const val FIND_BARCODE_DATA_KEY = "find_barcode_data"
    const val REPORT_OPERATION_KEY = "report_operation"
}