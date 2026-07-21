@file:Suppress("removal", "DEPRECATION")

package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.config

import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.MessagePostProcessor
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.interceptor.RabbitMdcInterceptor
import java.nio.charset.StandardCharsets


@Configuration
class RabbitConfig(
    private val props: RabbitProperties,
    private val rabbitMdcInterceptor: RabbitMdcInterceptor,
    private val objectMapper: ObjectMapper
) {
    private val logger = KotlinLogging.logger {}

    @Bean
    fun messageConverter(objectMapper: ObjectMapper): MessageConverter {
        return Jackson2JsonMessageConverter(objectMapper)
    }

    @Bean
    fun rabbitTemplate(
        connectionFactory: ConnectionFactory,
        messageConverter: MessageConverter
    ): RabbitTemplate = RabbitTemplate(connectionFactory).apply {
        setMessageConverter(messageConverter)
        setReplyTimeout(15000L)

        setBeforePublishPostProcessors(MessagePostProcessor { msg ->
            val messageId = msg.messageProperties.messageId ?: "NO_ID"
            logger.debug {
                val payload = String(msg.body, StandardCharsets.UTF_8)
                "[RabbitMQ SEND] MessageId: $messageId, Payload: $payload"
            }
            msg
        })

        setAfterReceivePostProcessors(
                MessagePostProcessor { msg ->
                    val messageId = msg.messageProperties.messageId ?: "NO_ID"
                        logger.debug {
                            val payload = String(msg.body, StandardCharsets.UTF_8)
                            "[RabbitMQ REPLY] MessageId: $messageId, Payload: $payload"
                        }
                    msg
                }
        )
    }

    @Bean
    fun rabbitListenerContainerFactory(
        connectionFactory: ConnectionFactory,
        messageConverter: MessageConverter
    ): SimpleRabbitListenerContainerFactory = SimpleRabbitListenerContainerFactory().apply {
        setConnectionFactory(connectionFactory)
        setMessageConverter(messageConverter)
        setDefaultRequeueRejected(false)
        setAdviceChain(rabbitMdcInterceptor)
    }


    @Bean
    fun measuredRemaindersDeclarables(): Declarables =
        RabbitDeclarablesBuilder.multiQueueDeclarables(
            exchangeName = props.getExchange(RabbitKeys.MEASURED_REMAINDERS_EXCHANGE ),
            queues = listOf(
                props.getQueue(RabbitKeys.FIND_MEASURED_REMAINDERS_QUEUE) to props.getKey(RabbitKeys.FIND_MEASURED_REMAINDERS_KEY),
                props.getQueue(RabbitKeys.UPDATE_MEASURED_REMAINDER_QUEUE) to props.getKey(RabbitKeys.UPDATE_MEASURED_REMAINDER_KEY),
                props.getQueue(RabbitKeys.INVENTORY_MEASURED_REMAINDERS_QUEUE) to props.getKey(RabbitKeys.INVENTORY_MEASURED_REMAINDERS_KEY)
            ).filter { it.second.isNotEmpty() }
        )

    @Bean
    fun inventoryDeclarables(): Declarables = RabbitDeclarablesBuilder.singleQueueDeclarables(
        exchangeName = props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
        queueName = props.getQueue(RabbitKeys.FIND_STOCK_LIST_QUEUE),
        routingKey = props.getKey(RabbitKeys.FIND_STOCK_LIST_KEY)
    )

    @Bean
    fun itemDeclarables(): Declarables = RabbitDeclarablesBuilder.singleQueueDeclarables(
        exchangeName = props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
        queueName = props.getQueue(RabbitKeys.GET_ITEM_DETAIL_QUEUE),
        routingKey = props.getKey(RabbitKeys.GET_ITEM_DETAIL_KEY)
    )

    @Bean
    fun lotDeclarables(): Declarables = RabbitDeclarablesBuilder.singleQueueDeclarables(
        exchangeName = props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
        queueName = props.getQueue(RabbitKeys.GET_LOT_DETAIL_QUEUE),
        routingKey = props.getKey(RabbitKeys.GET_LOT_DETAIL_KEY)
    )

    @Bean
    fun warehouseDeclarables(): Declarables = RabbitDeclarablesBuilder.multiQueueDeclarables(
        exchangeName = props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
        queues = listOf(
            props.getQueue(RabbitKeys.GET_WAREHOUSE_DETAIL_QUEUE) to props.getKey(RabbitKeys.GET_WAREHOUSE_DETAIL_KEY),
            props.getQueue(RabbitKeys.GET_LOCATION_DETAIL_QUEUE) to props.getKey(RabbitKeys.GET_LOCATION_DETAIL_KEY)
        )
    )

    @Bean
    fun containerDeclarables(): Declarables =
        RabbitDeclarablesBuilder.multiQueueDeclarables(
            exchangeName = props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
            queues = listOf(
                props.getQueue(RabbitKeys.GET_CONTAINER_QUEUE) to props.getKey(RabbitKeys.GET_CONTAINER_KEY),
                props.getQueue(RabbitKeys.FIND_CONTAINERS_QUEUE) to props.getKey(RabbitKeys.FIND_CONTAINERS_KEY)
            )
        )

    @Bean
    fun barcodeDeclarables(): Declarables = RabbitDeclarablesBuilder.singleQueueDeclarables(
        exchangeName = props.getExchange(RabbitKeys.BARCODE_EXCHANGE),
        queueName = props.getQueue(RabbitKeys.FIND_BARCODE_DATA_QUEUE),
        routingKey = props.getKey(RabbitKeys.FIND_BARCODE_DATA_KEY)
    )

    @Bean
    fun mechanicalPartDeclarables(): Declarables = RabbitDeclarablesBuilder.singleQueueDeclarables(
        exchangeName = props.getExchange(RabbitKeys.MECHANICAL_PART_EXCHANGE),
        queueName = props.getQueue(RabbitKeys.REPORT_OPERATION_QUEUE),
        routingKey = props.getKey(RabbitKeys.REPORT_OPERATION_KEY)
    )
}