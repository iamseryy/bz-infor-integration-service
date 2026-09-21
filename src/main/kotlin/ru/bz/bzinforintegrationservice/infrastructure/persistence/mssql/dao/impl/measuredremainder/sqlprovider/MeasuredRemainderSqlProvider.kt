package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.measuredremainder.sqlprovider

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.filter.MeasuredRemainderFilter
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.configuration.MssqlProperties


@Component
class MeasuredRemainderSqlProvider (
    private val props: MssqlProperties
){
    fun buildFindQuery(
            filter: MeasuredRemainderFilter
    ): Pair<String, MapSqlParameterSource> {
        val params = MapSqlParameterSource()

        val sql = buildString {
            append(
                """
                SELECT  whwmd530.t_huid AS ${EntityFieldsProvider.ID},
                        whwmd530.t_cdf_npor AS ${EntityFieldsProvider.CODE},
                        whwmd530.t_cdf_cprj AS ${EntityFieldsProvider.PROJECT_CODE},
                        tcibd001.t_dscb AS ${EntityFieldsProvider.MATERIAL},
                        whwmd530.t_cwar AS ${EntityFieldsProvider.WAREHOUSE_CODE},
                        whwmd530.t_cdf_loca AS ${EntityFieldsProvider.BIN_CODE},
                        whwmd530.t_cdf_lose AS ${EntityFieldsProvider.SEQUENCE},
                        whwmd530.t_cdf_most AS ${EntityFieldsProvider.STATUS},
                        whwmd530.t_cdf_ndoc AS ${EntityFieldsProvider.COMMENT},
                        CASE 
                            WHEN whwmd530.t_cdf_hght > 0 THEN whwmd530.t_cdf_hght 
                            ELSE whwmd530.t_hght 
                        END AS ${EntityFieldsProvider.LENGTH},
                        CASE 
                            WHEN whwmd530.t_cdf_wdth > 0 THEN whwmd530.t_cdf_wdth 
                            ELSE whwmd530.t_wdth 
                        END AS ${EntityFieldsProvider.WIDTH},
                        CASE 
                            WHEN whwmd530.t_cdf_thck > 0 THEN whwmd530.t_cdf_thck
                            ELSE whwmd530.t_dpth 
                        END AS ${EntityFieldsProvider.THICKNESS},
                        whwmd530.t_cdf_indt AS ${EntityFieldsProvider.INVENTORY_DATE}
                FROM    twhwmd530${props.inforCompany} AS whwmd530
                JOIN    ttcibd001${props.inforCompany} AS tcibd001 
                        ON whwmd530.t_item = tcibd001.t_item
                WHERE   whwmd530.t_cdf_mern = 1
                """.trimIndent()
            )

            with(filter) {
                if (!id.isNullOrEmpty()) {
                    append(" AND whwmd530.t_huid = :id")
                    params.addValue("id", id)
                }
                if (!codeLike.isNullOrEmpty()) {
                    append(" AND whwmd530.t_cdf_npor LIKE :remainderLike")
                    params.addValue("remainderLike", "%$codeLike%")
                }
                if (!projectCode.isNullOrEmpty()) {
                    append(" AND whwmd530.t_cdf_cprj = :projectCode")
                    params.addValue("projectCode", projectCode)
                }
                if (!materialLike.isNullOrEmpty()) {
                    append(" AND tcibd001.t_dscb LIKE :materialLike")
                    params.addValue("materialLike", "%$materialLike%")
                }
                if (!warehouseCode.isNullOrEmpty()) {
                    append(" AND whwmd530.t_cwar = :warehouseCode")
                    params.addValue("warehouseCode", warehouseCode)
                }
                if (!binCode.isNullOrEmpty()) {
                    append(" AND whwmd530.t_cdf_loca = :binCode")
                    params.addValue("binCode", binCode)
                }
                if (status != null) {
                    append(" AND whwmd530.t_cdf_most = :status")
                    params.addValue("status", status.number)
                }
                if (lengthFrom != null) {
                    append(" AND ((whwmd530.t_cdf_hght >= :lengthFrom) OR (whwmd530.t_cdf_hght = 0 AND whwmd530.t_hght >= :lengthFrom))")
                    params.addValue("lengthFrom", lengthFrom)
                }
                if (widthFrom != null) {
                    append(" AND ((whwmd530.t_cdf_wdth >= :widthFrom) OR (whwmd530.t_cdf_wdth = 0 AND whwmd530.t_wdth >= :widthFrom))")
                    params.addValue("widthFrom", widthFrom)
                }

                if (thicknessFrom != null) {
                    append(" AND ((whwmd530.t_cdf_thck >= :thicknessFrom) OR (whwmd530.t_cdf_thck = 0 AND whwmd530.t_dpth >= :thicknessFrom))")
                    params.addValue("thicknessFrom", thicknessFrom)
                }
            }
        }

        return sql to params
    }


    fun buildUpdateQuery(
        measuredRemainder: MeasuredRemainder
    ): Pair<String, MapSqlParameterSource> {
        val params = MapSqlParameterSource()

        val sql = buildString {
            append(
                """
                UPDATE twhwmd530${props.inforCompany} 
                SET t_cdf_loca = :binCode,
                    t_cdf_lose = :sequence,
                    t_cdf_most = :status,
                    t_cdf_hght = :length,
                    t_cdf_wdth = :width,
                    t_cdf_thck = :thickness,
                    t_cdf_ndoc = :comment,
                    t_cdf_indt = :inventoryDate
                WHERE t_huid = :id
                """.trimIndent()
            )
            with(measuredRemainder){
                params.addValue("id", id)
                params.addValue("binCode", location.binCode)
                params.addValue("sequence", location.sequence)
                params.addValue("status", status.number)
                params.addValue("length", dimensions.length)
                params.addValue("width", dimensions.width)
                params.addValue("thickness", dimensions.thickness)
                params.addValue("comment", comment)
                params.addValue("inventoryDate", inventoryDate)
            }
        }

        return sql to params
    }

    fun buildFindLocationsByWarehouseCodeQuery(warehouseCode: String?): Pair<String, MapSqlParameterSource> {
        val params = MapSqlParameterSource()

        val sql = buildString {
            append(
                """
                SELECT  whwmd300.t_loca AS ${EntityFieldsProvider.BIN_CODE},
                        whwmd300.t_cwar AS ${EntityFieldsProvider.WAREHOUSE_CODE}
                FROM twhwmd300${props.inforCompany} AS whwmd300
                WHERE whwmd300.t_zone = N'МО'
                """.trimIndent()
            )
            with(warehouseCode){
                if (!warehouseCode.isNullOrEmpty()) {
                    append(" AND whwmd300.t_cwar = :warehouseCode")
                    params.addValue("warehouseCode", warehouseCode)
                }
            }
        }

        return sql to params
    }


    fun buildFindMaterialsQuery(): Pair<String, MapSqlParameterSource> {
        val params = MapSqlParameterSource()

        val sql = buildString {
            append(
                """
                SELECT DISTINCT tcibd001.t_dscb AS ${EntityFieldsProvider.MATERIAL}
                FROM    twhwmd530${props.inforCompany} AS whwmd530
                JOIN    ttcibd001${props.inforCompany} AS tcibd001 
                        ON whwmd530.t_item = tcibd001.t_item
                WHERE   whwmd530.t_cdf_mern = 1
                        AND tcibd001.t_dscb <> ''
                """.trimIndent()
            )
        }

        return sql to params
    }

    fun buildFindProjectCodesQuery(): Pair<String, MapSqlParameterSource> {
        val params = MapSqlParameterSource()

        val sql = buildString {
            append(
                """
                SELECT DISTINCT whwmd530.t_cdf_cprj AS ${EntityFieldsProvider.PROJECT_CODE}
                FROM    twhwmd530${props.inforCompany} AS whwmd530
                WHERE   whwmd530.t_cdf_mern = 1
                        AND whwmd530.t_cdf_cprj <> ''
                """.trimIndent()
            )
        }

        return sql to params
    }

    fun buildFindWarehouseCodesQuery(): Pair<String, MapSqlParameterSource> {
        val params = MapSqlParameterSource()

        val sql = buildString {
            append(
                """
                SELECT DISTINCT whwmd530.t_cwar AS ${EntityFieldsProvider.WAREHOUSE_CODE}
                FROM    twhwmd530${props.inforCompany} AS whwmd530
                WHERE   whwmd530.t_cdf_mern = 1
                        AND whwmd530.t_cwar <> ''
                """.trimIndent()
            )
        }

        return sql to params
    }
}