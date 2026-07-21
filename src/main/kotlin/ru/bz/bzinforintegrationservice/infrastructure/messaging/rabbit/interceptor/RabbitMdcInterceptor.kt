package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.interceptor


import mu.KotlinLogging
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.slf4j.MDC
import org.springframework.amqp.core.Message
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.UUID

@Component
class RabbitMdcInterceptor : MethodInterceptor {

    private val logger = KotlinLogging.logger {}

    companion object {
        const val TRACE_ID_HEADER = "X-Trace-Id"
        const val MDC_TRACE_ID = "traceId"
        const val MDC_MESSAGE_ID = "messageId"
        const val MDC_SOURCE = "source"
        const val MDC_STATUS = "status"
    }

    override fun invoke(invocation: MethodInvocation): Any? {
        return if (isOutboundOperation(invocation)) {
            handleOutbound(invocation)
        } else {
            handleInbound(invocation)
        }
    }

    private fun handleOutbound(invocation: MethodInvocation): Any? {
        val methodName = invocation.method.name
        val message = findAmqpMessage(invocation.arguments)

        val traceId = extractTraceId(message) ?: UUID.randomUUID().toString()
        val messageId = message?.messageProperties?.messageId ?: "NO_ID"

        MDC.put(MDC_TRACE_ID, traceId)
        MDC.put(MDC_MESSAGE_ID, messageId)
        MDC.put(MDC_SOURCE, "RABBIT_SENDER")

        val startTime = System.currentTimeMillis()

        return try {
            logSendPayload(methodName, messageId, traceId, message)

            val result = invocation.proceed()
            val duration = System.currentTimeMillis() - startTime

            if (result is Message) {
                logReplyPayload(methodName, traceId, result, duration)
            } else {
                MDC.put(MDC_STATUS, "SUCCESS")
                logger.debug { "[RabbitMQ SEND] Completed. Method: $methodName, Duration: ${duration}ms" }
            }

            result
        } catch (e: Exception) {
            val duration = System.currentTimeMillis() - startTime
            MDC.put(MDC_STATUS, "ERROR")
            logger.error(e) { "[RabbitMQ SEND] Failed. Method: $methodName, Duration: ${duration}ms" }
            throw e
        } finally {
            clearMdc()
        }
    }

    private fun handleInbound(invocation: MethodInvocation): Any? {
        val methodName = invocation.method.name
        val message = findAmqpMessage(invocation.arguments)

        val traceId = extractTraceId(message) ?: UUID.randomUUID().toString()
        val messageId = message?.messageProperties?.messageId ?: "NO_ID"

        MDC.put(MDC_TRACE_ID, traceId)
        MDC.put(MDC_MESSAGE_ID, messageId)
        MDC.put(MDC_SOURCE, "RABBIT_LISTENER")

        val startTime = System.currentTimeMillis()

        return try {
            logger.debug { "[RabbitMQ RECEIVE] Processing started. Method: $methodName, MessageId: $messageId" }

            val result = invocation.proceed()
            val duration = System.currentTimeMillis() - startTime

            MDC.put(MDC_STATUS, "SUCCESS")
            logger.debug { "[RabbitMQ RECEIVE] Processing completed. Method: $methodName, Duration: ${duration}ms" }

            result
        } catch (e: Exception) {
            val duration = System.currentTimeMillis() - startTime
            MDC.put(MDC_STATUS, "ERROR")
            logger.error(e) { "[RabbitMQ RECEIVE] Processing failed. Method: $methodName, Duration: ${duration}ms" }
            throw e
        } finally {
            clearMdc()
        }
    }

    private fun isOutboundOperation(invocation: MethodInvocation): Boolean {
        val className = invocation.method.declaringClass.name
        return className.startsWith("org.springframework.amqp.rabbit.core")
    }

    private fun findAmqpMessage(args: Array<out Any?>): Message? {
        for (arg in args) {
            when (arg) {
                is Message -> return arg
                is org.springframework.messaging.Message<*> -> {
                    if (arg.payload is Message) {
                        return arg.payload as Message
                    }
                }
            }
        }
        return null
    }

    /**
     * ✅ ИСПРАВЛЕННЫЙ МЕТОД: используем карту headers
     */
    private fun extractTraceId(message: Message?): String? {
        if (message == null) return null
        return message.messageProperties.headers[TRACE_ID_HEADER]?.toString()
    }

    private fun logSendPayload(methodName: String, messageId: String, traceId: String, message: Message?) {
        if (message == null) {
            logger.debug { "[RabbitMQ SEND] Method: $methodName, MessageId: $messageId, TraceId: $traceId" }
            return
        }
        val payload = String(message.body, StandardCharsets.UTF_8)
        logger.debug { "[RabbitMQ SEND] Method: $methodName, MessageId: $messageId, TraceId: $traceId, Payload: $payload" }
    }

    private fun logReplyPayload(methodName: String, traceId: String, replyMessage: Message, duration: Long) {
        val payload = String(replyMessage.body, StandardCharsets.UTF_8)
        MDC.put(MDC_STATUS, "SUCCESS")
        logger.debug { "[RabbitMQ REPLY] Method: $methodName, TraceId: $traceId, Duration: ${duration}ms, Payload: $payload" }
    }

    private fun clearMdc() {
        MDC.remove(MDC_TRACE_ID)
        MDC.remove(MDC_MESSAGE_ID)
        MDC.remove(MDC_SOURCE)
        MDC.remove(MDC_STATUS)
    }
}