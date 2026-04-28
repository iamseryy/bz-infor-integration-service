package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.container.extractor

import org.springframework.stereotype.Component


@Component
data class ContainerExtractors(
    val containersExtractor: ContainersExtractor
)
