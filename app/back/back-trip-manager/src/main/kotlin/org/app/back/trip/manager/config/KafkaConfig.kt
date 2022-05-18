package org.app.back.trip.manager.config

import org.apache.kafka.clients.admin.Admin
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import java.util.*

//TODO Delete
class KafkaConfig {
    fun topicIn() {
        val properties = Properties();
        val topicName = "test-topic-app-1.0"
        properties.put(
            AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka+ssl://ec2-34-246-78-10.eu-west-1.compute.amazonaws.com:9096,kafka+ssl://ec2-34-246-81-10.eu-west-1.compute.amazonaws.com:9096,kafka+ssl://ec2-34-246-80-221.eu-west-1.compute.amazonaws.com:9096,kafka+ssl://ec2-34-242-239-67.eu-west-1.compute.amazonaws.com:9096,kafka+ssl://ec2-54-77-1-249.eu-west-1.compute.amazonaws.com:9096,kafka+ssl://ec2-54-72-228-10.eu-west-1.compute.amazonaws.com:9096,kafka+ssl://ec2-52-48-167-48.eu-west-1.compute.amazonaws.com:9096,kafka+ssl://ec2-52-214-17-78.eu-west-1.compute.amazonaws.com:9096"
        )

        Admin.create(properties).use { admin ->
            val partitions = 1
            val replicationFactor: Short = 1
            val newTopic = NewTopic(topicName, partitions, replicationFactor)
            val result = admin.createTopics(
                Collections.singleton(newTopic)
            )
            val future = result.values()[topicName]!!
            future.get()
        }
    }
}