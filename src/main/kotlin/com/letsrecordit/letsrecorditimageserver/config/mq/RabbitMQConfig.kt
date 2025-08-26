package com.letsrecordit.letsrecorditimageserver.config.mq

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig(
    @Value("\${spring.rabbitmq.host}")
    private val rabbitmqHost: String,

    @Value("\${spring.rabbitmq.port}")
    private val rabbitmqPort: Int,

    @Value("\${spring.rabbitmq.username}")
    private val rabbitmqUsername: String,

    @Value("\${spring.rabbitmq.password}")
    private val rabbitmqPassword: String,

    @Value("\${rabbitmq.queue.name}")
    private val queueName: String,

    @Value("\${rabbitmq.exchange.name}")
    private val exchangeName: String,

    @Value("\${rabbitmq.routing.key}")
    private val routingKey: String,
) {

    @Bean
    fun queue(): Queue = Queue(queueName)

    @Bean
    fun exchange(): DirectExchange = DirectExchange(exchangeName)

    @Bean
    fun binding(queue: Queue, exchange: DirectExchange): Binding =
        BindingBuilder.bind(queue).to(exchange).with(routingKey)

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val connectionFactory = CachingConnectionFactory()
        connectionFactory.setHost(rabbitmqHost)
        connectionFactory.port = rabbitmqPort
        connectionFactory.username = rabbitmqUsername
        connectionFactory.setPassword(rabbitmqPassword)

        return connectionFactory
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = jackson2JsonMessageConverter()
        return rabbitTemplate
    }

    @Bean
    fun jackson2JsonMessageConverter(): MessageConverter = Jackson2JsonMessageConverter()
}