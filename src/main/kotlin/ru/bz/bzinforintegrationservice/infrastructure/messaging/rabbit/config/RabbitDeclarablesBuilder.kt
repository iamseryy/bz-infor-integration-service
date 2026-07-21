package ru.bz.bzinforintegrationservice.infrastructure.messaging.rabbit.config

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Declarable
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue



object RabbitDeclarablesBuilder {
    fun singleQueueDeclarables(
        exchangeName: String,
        queueName: String,
        routingKey: String
    ): Declarables {
        val exchange = DirectExchange(exchangeName, true, false)
        val queue = Queue(queueName, true)
        val binding = BindingBuilder.bind(queue).to(exchange).with(routingKey)

        return Declarables(exchange, queue, binding)
    }

    fun multiQueueDeclarables(
        exchangeName: String,
        queues: List<Pair<String, String>>
    ): Declarables {
        val exchange = DirectExchange(exchangeName, true, false)
        val declarables = mutableListOf<Declarable>(exchange)

        queues.forEach { (queueName, routingKey) ->
            val queue = Queue(queueName, true)
            val binding = BindingBuilder.bind(queue).to(exchange).with(routingKey)
            declarables.add(queue)
            declarables.add(binding)
        }

        return Declarables(declarables)
    }
}