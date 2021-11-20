package store.micronaut.kotlin.repositories

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import jakarta.inject.Singleton
import store.micronaut.kotlin.domain.Checkout

@Singleton
class CheckoutRepositoryImpl(
    private val mongoClient: MongoClient
) : CheckoutRepository {
    override fun save(checkout: Checkout): Boolean {
        return getCollection().insertOne(checkout).wasAcknowledged()
    }

    override fun findById(checkoutId: String): Checkout? {
        return getCollection().find(eq("_id", checkoutId)).first()
    }

    override fun getAll(): List<Checkout> {
        return getCollection().find().toList()
    }

    private fun getCollection(): MongoCollection<Checkout> {
        return mongoClient.getDatabase("store")
            .getCollection("checkout", Checkout::class.java)
    }
}