package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.configuration

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "application.mssql")
class MssqlProperties {
    //@field:NotBlank(message = "Property 'application.mssql.infor_company' must be set")
    var inforCompany: String = ""
}