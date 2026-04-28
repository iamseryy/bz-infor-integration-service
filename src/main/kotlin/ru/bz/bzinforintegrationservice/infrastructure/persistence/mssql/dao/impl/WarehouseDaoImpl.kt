package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.WarehouseDao
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.mapper.GetBinDetailByShotFilterMapper
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.warehouse.toBin
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.warehouse.toWarehouse
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchBinDetailShotFilter
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Bin
import ru.bz.bzinforintegrationservice.domain.model.warehouse.Warehouse


@Service
class WarehouseDaoImpl(
    private val jdbcTemplate: JdbcTemplate,
    private val getBinDetailByShotFilterMapper: GetBinDetailByShotFilterMapper,
    @Value("\${application.infor_company}") private val company: String
): WarehouseDao {
    override fun getWarehouseDetail(warehouseCode: String): Warehouse? =
        jdbcTemplate.query(buildSqlQueryGetWarehouseDetail(warehouseCode), WarehouseDao.Companion.warehouseDetailRowMapper).firstOrNull()?.toWarehouse()

    private fun buildSqlQueryGetWarehouseDetail(warehouseCode: String) =
        """
                SELECT  TOP(1)
                        tcmcs003.t_cwar AS ${EntityFieldsProvider.WAREHOUSE_CODE},
                        tcmcs003.t_dsca AS ${EntityFieldsProvider.WAREHOUSE_DESC}
                FROM    ttcmcs003$company AS tcmcs003
                WHERE   tcmcs003.t_cwar = '${warehouseCode}' 
            """.trimIndent()



    override fun getBinDetailByShotFilter(filter: SearchBinDetailShotFilter): Bin? =
        jdbcTemplate.query(buildSqlQueryGetBinDetailByShotFilter(filter), getBinDetailByShotFilterMapper).firstOrNull()?.toBin()

    private fun buildSqlQueryGetBinDetailByShotFilter(filter: SearchBinDetailShotFilter) =
        """
                SELECT  TOP(1)
                        whwmd300.t_cwar AS ${EntityFieldsProvider.WAREHOUSE_CODE},
                        whwmd300.t_loca AS ${EntityFieldsProvider.BIN_CODE},
                        whwmd300.t_dsca AS ${EntityFieldsProvider.BIN_DESC}
                FROM    twhwmd300$company AS whwmd300
                WHERE   whwmd300.t_cwar = '${filter.warehouseCode}' AND whwmd300.t_loca = N'${filter.binCode}'  
            """.trimIndent()
}