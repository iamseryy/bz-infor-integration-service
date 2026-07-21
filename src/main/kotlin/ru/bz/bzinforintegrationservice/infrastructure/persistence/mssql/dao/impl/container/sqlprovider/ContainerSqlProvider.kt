package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.container.sqlprovider

import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.filter.SearchContainerFilterDto


private const val COMPANY_PROPERTY = "application.mssql.infor_company"

@Component
class ContainerSqlProvider (
    environment: Environment,
    private val fieldsProvider: EntityFieldsProvider
){
    private val company = environment.getProperty(COMPANY_PROPERTY)

    fun findContainersByFilter(filter: SearchContainerFilterDto) =
        """WITH containers_detail AS (
                SELECT  zbwmd550.t_idcn AS ${fieldsProvider.CODE},
                            zbwmd550.t_owar AS ${fieldsProvider.WAREHOUSE_OWNER_CODE},
                            zbwmd550.t_dsca AS ${fieldsProvider.DESCRIPTION},
                            zbwmd551.t_stat AS ${fieldsProvider.STATUS_CODE},
                            zbwmd551.t_cwar AS ${fieldsProvider.CONTAINER_WAREHOUSE_CODE},
                            zbwmd551.t_loca AS ${fieldsProvider.CONTAINER_BIN_CODE},
                            zbwmd551.t_logn AS ${fieldsProvider.USER_LOGIN_LAST_MODIFIED},
                            zbwmd551.t_udat AS ${fieldsProvider.DATE_LAST_MODIFIED},
                            zbwmd555.t_sern AS ${fieldsProvider.LINE},
                            zbwmd555.t_oorg AS ${fieldsProvider.WAREHOUSE_ORDER_ORIGIN},
                            zbwmd555.t_orno AS ${fieldsProvider.WAREHOUSE_ORDER_CODE},
                            zbwmd555.t_oset AS ${fieldsProvider.WAREHOUSE_ORDER_SET},
                            zbwmd555.t_pono AS ${fieldsProvider.WAREHOUSE_ORDER_LINE},
                            zbwmd555.t_seqn AS ${fieldsProvider.WAREHOUSE_ORDER_SEQUENCE},
                            zbwmd555.t_item AS ${fieldsProvider.ITEM_CODE},
                            tcibd001.t_dsca AS ${fieldsProvider.ITEM_DESC},
                            tcibd001.t_cuni AS ${fieldsProvider.UNIT_CODE},
                            tcmcs001.t_dsca AS ${fieldsProvider.UNIT_DESC},
                            zbwmd555.t_clot AS ${fieldsProvider.LOT},
                            zbwmd555.t_quan AS ${fieldsProvider.QUANTITY},
                            zbwmd555.t_cwar AS ${fieldsProvider.WAREHOUSE_CODE},
                            zbwmd555.t_loca AS ${fieldsProvider.BIN_CODE}
                    FROM    tzbwmd550$company AS zbwmd550
                    LEFT JOIN tzbwmd551$company AS zbwmd551 ON zbwmd550.t_idcn = zbwmd551.t_idcn 
                    LEFT JOIN tzbwmd555$company AS zbwmd555 ON zbwmd550.t_idcn = zbwmd555.t_idcn
                    LEFT JOIN ttcibd001$company AS tcibd001 ON zbwmd555.t_item = tcibd001.t_item
                    LEFT JOIN ttcmcs001$company AS tcmcs001 ON tcibd001.t_cuni = tcmcs001.t_cuni   
                    ${buildSqlWhereClause(filter)}       
            ), containers AS (
                SELECT DISTINCT ${fieldsProvider.CODE} AS container_code
                FROM containers_detail
                ORDER BY ${fieldsProvider.CODE} 
                OFFSET ${filter.page * filter.pageSize} ROWS FETCH NEXT ${filter.pageSize} ROWS ONLY
            )
            SELECT  ${fieldsProvider.CODE},
                    ${fieldsProvider.WAREHOUSE_OWNER_CODE},
                    ${fieldsProvider.DESCRIPTION},
                    ${fieldsProvider.STATUS_CODE},
                    ${fieldsProvider.CONTAINER_WAREHOUSE_CODE},
                    ${fieldsProvider.CONTAINER_BIN_CODE},
                    ${fieldsProvider.USER_LOGIN_LAST_MODIFIED},
                    ${fieldsProvider.DATE_LAST_MODIFIED},
                    ${fieldsProvider.LINE},
                    ${fieldsProvider.WAREHOUSE_ORDER_ORIGIN},
                    ${fieldsProvider.WAREHOUSE_ORDER_CODE},
                    ${fieldsProvider.WAREHOUSE_ORDER_SET},
                    ${fieldsProvider.WAREHOUSE_ORDER_LINE},
                    ${fieldsProvider.WAREHOUSE_ORDER_SEQUENCE},
                    ${fieldsProvider.ITEM_CODE},
                    ${fieldsProvider.ITEM_DESC},
                    ${fieldsProvider.UNIT_CODE},
                    ${fieldsProvider.UNIT_DESC},
                    ${fieldsProvider.LOT},
                    ${fieldsProvider.QUANTITY},
                    ${fieldsProvider.WAREHOUSE_CODE},
                    ${fieldsProvider.BIN_CODE}
            FROM    containers_detail
            JOIN    containers ON ${fieldsProvider.CODE} = container_code
            ORDER BY ${fieldsProvider.CODE}, ${fieldsProvider.LINE}
        """.trimIndent()

    private fun buildSqlWhereClause(filter: SearchContainerFilterDto): String =
        mutableListOf<String>()
            .also { conditions ->
                with ( filter ) {
                    if (!containerCode.isNullOrEmpty()) conditions.add("zbwmd550.t_idcn = N'$containerCode'")
                    if (!warehouseOwnerCode.isNullOrEmpty()) conditions.add("zbwmd550.t_owar = N'$warehouseOwnerCode'")
                    if (!containerDescription.isNullOrEmpty()) conditions.add("zbwmd550.t_dsca = N'$containerDescription'")
                    if (status != null) conditions.add("zbwmd551.t_stat = '${status.number}'")
                    if (!containerLocationWarehouseCode.isNullOrEmpty()) conditions.add("zbwmd555.t_cwar = N'$containerLocationWarehouseCode'")
                    if (!containerLocationBinCode.isNullOrEmpty()) conditions.add("zbwmd555.t_loca = N'$containerLocationBinCode'")
                    if (!warehouseOrderCode.isNullOrEmpty()) conditions.add("zbwmd555.t_orno = N'$warehouseOrderCode'")
                    if (!itemCode.isNullOrEmpty()) conditions.add("zbwmd555.t_item = N'         $itemCode'")
                    if (!lotCode.isNullOrEmpty()) conditions.add("zbwmd555.t_clot = N'$lotCode'")
                    if (!stockPositionLocationWarehouseCode.isNullOrEmpty()) conditions.add("zbwmd555.t_cwar = N'$stockPositionLocationWarehouseCode'")
                    if (!stockPositionLocationBinCode.isNullOrEmpty()) conditions.add("zbwmd555.t_loca = N'$stockPositionLocationBinCode'")
                }
            }.let { conditions ->
                if (conditions.isNotEmpty()) "WHERE ${conditions.joinToString(" AND ")}" else ""
            }
}


