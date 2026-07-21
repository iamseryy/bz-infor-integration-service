package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.config

import org.springframework.boot.context.properties.ConfigurationProperties



@ConfigurationProperties(prefix = "application.rabbitmq")
class RabbitProperties(
    val exchange: Map<String, String>,
    val queue: Map<String, String>,
    val key: Map<String, String>
) {
    fun getExchange(name: String): String =
        exchange[name] ?: throw IllegalStateException("Exchange '$name' not configured")

    fun getQueue(name: String): String =
        queue[name] ?: throw IllegalStateException("Queue '$name' not configured")

    fun getKey(name: String): String =
        key[name] ?: throw IllegalStateException("Routing key '$name' not configured")
}