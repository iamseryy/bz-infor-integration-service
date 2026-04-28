package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.EntityFieldsProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.ItemDao
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.item.toItem
import ru.bz.bzinforintegrationservice.domain.model.Item.Item


@Component
class ItemDaoImpl(
    private val jdbcTemplate: JdbcTemplate,
    @Value("\${application.infor_company}") private val company: String
): ItemDao {
    override fun getItemDetail(itemCode: String): Item? =
        jdbcTemplate.query(buildSqlQuery(itemCode), ItemDao.Companion.itemRowMapper).firstOrNull()?.toItem()

    private fun buildSqlQuery(itemCode: String) =
        """
                SELECT  TOP(1) 
                        tcibd001.t_item AS ${EntityFieldsProvider.ITEM_CODE},
                        tcibd001.t_cdf_cdf2 AS ${EntityFieldsProvider.ITEM_DESC},
                        tcibd001.t_cuni AS ${EntityFieldsProvider.UNIT_CODE},
                        tcmcs001.t_dsca AS ${EntityFieldsProvider.UNIT_DESC}
                FROM    ttcibd001$company AS tcibd001
                JOIN    ttcmcs001$company AS tcmcs001 ON tcibd001.t_cuni = tcmcs001.t_cuni
                WHERE   tcibd001.t_item = '         ${itemCode}' 
            """.trimIndent()
}