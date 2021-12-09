package store.micronaut.kotlin.factories

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton

@Factory
internal class DatabaseFactory(
    private val mongoClient: MongoClient
) {
    @Singleton
    fun getDatabase(): MongoDatabase {
        return mongoClient.getDatabase("store")
    }
}