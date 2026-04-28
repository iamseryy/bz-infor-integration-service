package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.mapper

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.warehouse.BinDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.warehouse.getInstance
import java.sql.ResultSet


@Component
class GetBinDetailByShotFilterMapper: RowMapper<BinDto> {
    override fun mapRow(rs: ResultSet, rowNum: Int): BinDto = BinDto.getInstance(rs)
}