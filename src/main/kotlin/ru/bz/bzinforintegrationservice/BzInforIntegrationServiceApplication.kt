package ru.bz.bzinforintegrationservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.config.RabbitProperties
import ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.configuration.MssqlProperties

@SpringBootApplication
@EnableConfigurationProperties(RabbitProperties::class, MssqlProperties::class)
class BzInforIntegrationServiceApplication
fun main(args: Array<String>) {
    runApplication<BzInforIntegrationServiceApplication>(*args)
}
