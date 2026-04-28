package ru.bz.bzinforintegrationservice.presentation.rabbit.configuration

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import mu.KotlinLogging
import org.springframework.amqp.core.Message
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.stereotype.Component
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException



@Component
class LoggingJackson2JsonMessageConverter : Jackson2JsonMessageConverter(
    createObjectMapper()
) {

    private val logger = KotlinLogging.logger {}

    private companion object {
        private const val MAX_LOG_LENGTH = 4096

        fun createObjectMapper(): ObjectMapper {
            return ObjectMapper().apply {
                registerModule(JavaTimeModule())
                disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                enable(SerializationFeature.INDENT_OUTPUT)
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            }
        }
    }

    override fun fromMessage(message: Message): Any {
        try {
            if (message.body == null) {
                logger.error { "Message body is null, cannot process message" }
                throw IllegalArgumentException("Message body is null")
            }

            if (message.body.isEmpty()) {
                logger.error { "Message body is empty, cannot process message" }
                throw IllegalArgumentException("Message body is empty")
            }

            val rawJson = try {
                String(message.body, Charsets.UTF_8)
            } catch (e: UnsupportedCharsetException) {
                logger.warn(e) { "Unsupported charset in message body, using default" }
                String(message.body, Charset.defaultCharset())
            }


            if (logger.isInfoEnabled) {
                val logJson = if (rawJson.length > MAX_LOG_LENGTH) {
                    "${rawJson.take(MAX_LOG_LENGTH)}... [truncated]"
                } else {
                    rawJson
                }
                logger.info { "Raw JSON from RabbitMQ:\n$logJson" }
            }

            if (logger.isDebugEnabled && message.messageProperties != null) {
                val headers = message.messageProperties.headers
                if (headers != null && headers.isNotEmpty()) {
                    logger.debug {
                        "RabbitMQ headers:\n" + headers.entries
                            .joinToString(separator = "\n") { "  ${it.key} = ${it.value}" }
                    }
                } else {
                        logger.debug { "RabbitMQ headers: empty or null" }
                    }
            }

            return super.fromMessage(message)

            } catch (e: Exception) {  // Catch для внешнего try
                logger.error(e) { "Failed to process and log RabbitMQ message" }
                throw e
            }
        }
}
