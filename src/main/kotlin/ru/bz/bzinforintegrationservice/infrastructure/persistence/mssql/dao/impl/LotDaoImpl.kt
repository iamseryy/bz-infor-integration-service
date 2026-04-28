package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.LotDao
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.lot.toLotDetail
import ru.bz.bzinforintegrationservice.domain.model.filter.SearchLotDetailShotFilter
import ru.bz.bzinforintegrationservice.domain.model.lot.LotDetail


@Component
class LotDaoImpl(
    private val jdbcTemplate: JdbcTemplate,
    @Value("\${application.infor_company}") private val company: String
): LotDao {
    override fun getLotDetail(filter: SearchLotDetailShotFilter): LotDetail? =
        jdbcTemplate.query(buildSqlQuery(filter), LotDao.Companion.lotDetailRowMapper).firstOrNull()?.toLotDetail()


    private fun buildSqlQuery(filter: SearchLotDetailShotFilter) =
        """
                SELECT  TOP(1) 
                        whltc100.t_item AS ${EntityFieldsProvider.ITEM_CODE},
                        whltc100.t_clot AS ${EntityFieldsProvider.LOT_CODE},
                        whltc100.t_effn AS ${EntityFieldsProvider.EFFECTIVITY_UNIT_CODE},
                        whltc100.t_ltor AS ${EntityFieldsProvider.LOT_CODE_PARENT},
                        whltc100.t_cdf_lotb AS ${EntityFieldsProvider.LOT_CODE_SOURCE},
                        tcibd001.t_cdf_cdf2 AS ${EntityFieldsProvider.ITEM_DESC},
                        tcmcs001.t_cuni AS ${EntityFieldsProvider.UNIT_CODE},
                        tcmcs001.t_dsca AS ${EntityFieldsProvider.UNIT_DESC}
                FROM    twhltc100$company AS whltc100
                JOIN    ttcibd001$company AS tcibd001 ON whltc100.t_item = tcibd001.t_item
                JOIN    ttcmcs001$company AS tcmcs001 ON tcibd001.t_cuni = tcmcs001.t_cuni
                WHERE   whltc100.t_item = '         ${filter.itemCode}' AND whltc100.t_clot = '${filter.lotCode}'
            """.trimIndent()
}