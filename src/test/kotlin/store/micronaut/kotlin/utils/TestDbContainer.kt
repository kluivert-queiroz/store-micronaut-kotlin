package store.micronaut.kotlin.utils

import org.testcontainers.containers.MongoDBContainer

class TestDbContainer: MongoDBContainer() {
    companion object {
        private lateinit var instance: TestDbContainer

        fun start() {
            if (!Companion::instance.isInitialized) {
                instance = TestDbContainer()
                instance.dockerImageName = "mongo:4.4"
                instance.start()

                val uri = "mongodb://${instance.host}:${instance.getMappedPort(27017)}/store"
                System.setProperty("mongodb.uri", uri)
            }
        }

        fun stop() {
            if (Companion::instance.isInitialized) {
                instance.stop()
            }
        }
    }
}