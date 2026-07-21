package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.container

import mu.KotlinLogging
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.ContainerDao
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.container.extractor.ContainerExtractors
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.container.sqlprovider.ContainerSqlProvider
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.container.ContainerDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.container.ContainersPaginationDto
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.entity.filter.SearchContainerFilterDto


@Component
class ContainerDaoImpl (
    private val jdbcTemplate: JdbcTemplate,
    private val containerSqlProvider: ContainerSqlProvider,
    private val containerExtractors: ContainerExtractors
): ContainerDao {
    private val logger = KotlinLogging.logger {}

    override fun getContainerDetailByCode(code: String): ContainerDto? =
        SearchContainerFilterDto(containerCode = code).let {filter ->
            findContainersDetailByFilter(filter).containers.firstOrNull()
        }

    override fun findContainersDetailByFilter(filter: SearchContainerFilterDto): ContainersPaginationDto =
        jdbcTemplate.query(
            containerSqlProvider.findContainersByFilter(filter),
            containerExtractors.containersExtractor()
        ).let {containers ->
            ContainersPaginationDto(
                containers = containers ?: emptyList(),
                page = filter.page,
                pageSize = filter.pageSize)
        }.also {
            val test = containerSqlProvider.findContainersByFilter(filter)
            logger.debug { "Executing SQL: $test" }
        }
}