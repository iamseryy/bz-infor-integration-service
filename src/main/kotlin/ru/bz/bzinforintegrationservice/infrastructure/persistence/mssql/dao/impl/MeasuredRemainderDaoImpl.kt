package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.MeasuredRemainderDao
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.measuredremainder.toMeasuredRemainder
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchMeasuredRemainderFilter
import ru.bz.bzinforintegrationservice.domain.model.measuredremainder.MeasuredRemaindersPagination


@Component
class MeasuredRemainderDaoImpl(
    private val jdbcTemplate: JdbcTemplate,
    @Value("\${application.infor_company}") private val company: String
): MeasuredRemainderDao {
    override fun findByFilter(filter: SearchMeasuredRemainderFilter): MeasuredRemaindersPagination =
        try {
            jdbcTemplate.query(buildSqlQuery(filter), MeasuredRemainderDao.Companion.rowMapper)
                .toList()
                .let {
                    MeasuredRemaindersPagination(
                        measuredRemainders = it.map { measuredRemainderDto ->  measuredRemainderDto.toMeasuredRemainder()},
                        page = filter.page,
                        pageSize = filter.pageSize

                    )
                }
        } catch (e: EmptyResultDataAccessException) {
            MeasuredRemaindersPagination(
                measuredRemainders = emptyList(),
                page = filter.page,
                pageSize = filter.pageSize

            )
        }

    private fun buildSqlQuery(filter: SearchMeasuredRemainderFilter) =
        """
                SELECT  whwmd530.t_huid AS ${EntityFieldsProvider.ID},
                        whwmd530.t_cdf_npor AS ${EntityFieldsProvider.REMAINDER},
                        whwmd530.t_cdf_cprj AS ${EntityFieldsProvider.PROJECT_CODE},
                        tcibd001.t_dscb AS ${EntityFieldsProvider.MATERIAL},
                        whwmd530.t_cwar AS ${EntityFieldsProvider.WAREHOUSE_CODE},
                        whwmd530.t_cdf_loca AS ${EntityFieldsProvider.BIN_CODE},
                        whwmd530.t_cdf_lose AS ${EntityFieldsProvider.SEQUENCE},
                        whwmd530.t_cdf_most AS ${EntityFieldsProvider.STATUS},
                        whwmd530.t_cdf_ndoc AS ${EntityFieldsProvider.COMMENT},
                        CASE 
                            WHEN whwmd530.t_cdf_hght > 0 
                            THEN whwmd530.t_cdf_hght 
                            ELSE whwmd530.t_hght 
                        END AS ${EntityFieldsProvider.LENGTH},
                        CASE 
                            WHEN whwmd530.t_cdf_wdth > 0 
                            THEN whwmd530.t_cdf_wdth 
                            ELSE whwmd530.t_wdth 
                        END AS ${EntityFieldsProvider.WIDTH},
                        whwmd530.t_dpth AS ${EntityFieldsProvider.DEPTH}
                FROM    twhwmd530$company AS whwmd530
                JOIN    ttcibd001$company AS tcibd001 ON whwmd530.t_item = tcibd001.t_item
                WHERE   ${buildSqlWhereClause(filter)} 
                ORDER BY whwmd530.t_huid 
                OFFSET ${filter.page * filter.pageSize} ROWS FETCH NEXT ${filter.pageSize} ROWS ONLY
            """.trimIndent()


    private fun buildSqlWhereClause(filter: SearchMeasuredRemainderFilter) = mutableListOf<String>()
        .apply {
            with(filter) {
                if (!id.isNullOrEmpty()) add("whwmd530.t_huid = '${id}'")
                if (!remainderLike.isNullOrEmpty()) add("whwmd530.t_cdf_npor like N'%${remainderLike}%'")
                if (!projectCode.isNullOrEmpty()) add("whwmd530.t_cdf_cprj = '${projectCode}'")
                if (!materialLike.isNullOrEmpty()) add("tcibd001.t_dscb like N'%${materialLike}%'")
                if (!warehouseCode.isNullOrEmpty()) add("whwmd530.t_cwar = '${warehouseCode}'")
                if (!binCode.isNullOrEmpty()) add("whwmd530.t_cdf_loca = '${binCode}'")
                if (status != null) add("whwmd530.t_cdf_most = '${status.number}'")
                if (!comment.isNullOrEmpty()) add("whwmd530.t_cdf_ndoc = '${comment}'")

                if (lengthFrom != null) {
                    add(
                        "((whwmd530.t_cdf_hght >= '${lengthFrom}') OR " +
                                "(whwmd530.t_cdf_hght = 0 AND whwmd530.t_hght >= '${lengthFrom}'))"
                    )
                }

                if (widthFrom != null) {
                    add(
                        "((whwmd530.t_cdf_wdth >= '${widthFrom}') OR " +
                                "(whwmd530.t_cdf_wdth = 0 AND whwmd530.t_wdth >= '${widthFrom}'))"
                    )
                }

                if (depth != null) add("whwmd530.t_wdth = '${depth}'")
            }

            add("whwmd530.t_cdf_mern = 1")
        }
        .joinToString(" AND ")
}