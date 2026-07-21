package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.inventory

import org.springframework.core.env.Environment
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.InventoryDao
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.stock.toStock
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchInventoryBalanceFilter
import ru.bz.bzinforintegrationservice.domain.model.stock.StockListPagination


@Component
class InventoryDaoImpl(
    private val jdbcTemplate: JdbcTemplate,
    private val environment: Environment
): InventoryDao {
    override fun findInventoryBalanceByFilter(filter: SearchInventoryBalanceFilter): StockListPagination =
        try {
            jdbcTemplate.query(buildSqlQuery(filter), InventoryDao.Companion.rowMapper)
                .toList()
                .let {
                    StockListPagination(
                        stockList = it.map { stockDto ->  stockDto.toStock() },
                        page = filter.page,
                        pageSize = filter.pageSize
                    )
                }
        } catch (e: EmptyResultDataAccessException) {
            StockListPagination(
                stockList = emptyList(),
                page = filter.page,
                pageSize = filter.pageSize
            )
        }


    private fun buildSqlQuery(filter: SearchInventoryBalanceFilter): String =
        if(filter.findLotChildren) getStockWithChildrenQuery(filter) else getStockByFilterQuery(filter)

    private fun getStockByFilterQuery(filter: SearchInventoryBalanceFilter): String =
        """WITH ${getFullStockTableQuery()}
                SELECT * 
                FROM stock
                ${buildSqlWhereClause(filter)} 
                ORDER BY stock.${EntityFieldsProvider.WAREHOUSE_CODE}, stock.${EntityFieldsProvider.BIN_CODE}, stock.${EntityFieldsProvider.ITEM_CODE}, stock.${EntityFieldsProvider.LOT_CODE}
                OFFSET ${filter.page * filter.pageSize} ROWS FETCH NEXT ${filter.pageSize} ROWS ONLY
            """.trimIndent()

    private fun getStockWithChildrenQuery(filter: SearchInventoryBalanceFilter): String =
        """
                WITH ${getFullStockTableQuery()}, ${getLotWithChildrenTableQuery(filter)} 
                SELECT * 
                FROM stock
                JOIN party_hierarchy ON stock.${EntityFieldsProvider.ITEM_CODE} = party_hierarchy.${EntityFieldsProvider.ITEM_CODE} and stock.${EntityFieldsProvider.LOT_CODE} = party_hierarchy.${EntityFieldsProvider.LOT_CODE}
                ORDER BY stock.${EntityFieldsProvider.WAREHOUSE_CODE}, stock.${EntityFieldsProvider.BIN_CODE}, stock.${EntityFieldsProvider.ITEM_CODE}, stock.${EntityFieldsProvider.LOT_CODE}
                OFFSET ${filter.page * filter.pageSize} ROWS FETCH NEXT ${filter.pageSize} ROWS ONLY
            """.trimIndent()


    private fun getFullStockTableQuery(): String =
        environment.getProperty("application.mssql.infor_company").let { company ->
            """stock AS (
                            SELECT  whinr140.t_cwar AS ${EntityFieldsProvider.WAREHOUSE_CODE},
                                    whinr140.t_loca AS ${EntityFieldsProvider.BIN_CODE},
                                    whinr140.t_item AS ${EntityFieldsProvider.ITEM_CODE},
                                    tcibd001.t_cdf_cdf2 AS ${EntityFieldsProvider.ITEM_DESC},
                                    tcibd001.t_cuni AS ${EntityFieldsProvider.UNIT_CODE},
                                    tcmcs001.t_dsca AS ${EntityFieldsProvider.UNIT_DESC},
                                    whinr140.t_clot AS ${EntityFieldsProvider.LOT_CODE},
                                    whinr140.t_qhnd AS ${EntityFieldsProvider.QUANTITY_AVAILABLE},
                                    whinr140.t_qblk AS ${EntityFieldsProvider.QUANTITY_BLOCKED},
                                    whinr140.t_qlal AS ${EntityFieldsProvider.QUANTITY_ALLOCATED}
                            FROM    twhinr140$company AS whinr140
                            JOIN    ttcibd001$company AS tcibd001 ON whinr140.t_item = tcibd001.t_item
                            JOIN    ttcmcs001$company AS tcmcs001 ON tcibd001.t_cuni = tcmcs001.t_cuni
                        )
            """.trimIndent()
        }

    private fun getLotWithChildrenTableQuery(filter: SearchInventoryBalanceFilter): String =
        environment.getProperty("application.mssql.infor_company").let { company ->
            """party_hierarchy AS (
                        SELECT  whltc100.t_clot AS ${EntityFieldsProvider.LOT_CODE},
                                whltc100.t_item AS ${EntityFieldsProvider.ITEM_CODE},
                                whltc100.t_ltor AS ${EntityFieldsProvider.LOT_CODE_ORIGINAL},
                                whltc100.t_clot AS ${EntityFieldsProvider.LOT_CODE_FINAL}
                        FROM twhltc100$company as whltc100
                        WHERE whltc100.t_item = '         ${filter.itemCode}' AND whltc100.t_clot = '${filter.lotCode}' 
                        UNION ALL
                        SELECT  p.t_clot AS ${EntityFieldsProvider.LOT_CODE},
                                p.t_item AS ${EntityFieldsProvider.ITEM_CODE},
                                p.t_ltor AS ${EntityFieldsProvider.LOT_CODE_ORIGINAL},
                                ph.${EntityFieldsProvider.LOT_CODE} AS ${EntityFieldsProvider.LOT_CODE_FINAL}
                        FROM twhltc100$company as p
                        JOIN party_hierarchy ph ON p.t_ltor = ph.${EntityFieldsProvider.LOT_CODE_FINAL}
                    )    
            """.trimIndent()
        }

    private fun buildSqlWhereClause(filter: SearchInventoryBalanceFilter): String = mutableListOf<String>()
        .also { conditions ->
            with( filter ) {
                if (!warehouseCode.isNullOrEmpty()) conditions.add("stock.${EntityFieldsProvider.WAREHOUSE_CODE} = '${warehouseCode}'")
                if (!binCode.isNullOrEmpty()) conditions.add("stock.${EntityFieldsProvider.BIN_CODE} = N'${binCode}'")
                if (!itemCode.isNullOrEmpty()) conditions.add("stock.${EntityFieldsProvider.ITEM_CODE} = '         ${itemCode}'")
                if (!lotCode.isNullOrEmpty()) conditions.add("stock.${EntityFieldsProvider.LOT_CODE} = '${lotCode}'")
            }
        }.let { conditions ->
            if (conditions.isNotEmpty()) "WHERE ${conditions.joinToString(" AND ")}" else ""
        }

}