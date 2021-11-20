package store.micronaut.kotlin.repositories

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import jakarta.inject.Singleton
import store.micronaut.kotlin.domain.Customer

@Singleton
class CustomerRepositoryImpl(
    private val mongoClient: MongoClient
) : CustomerRepository {
    override fun save(customer: Customer): Boolean {
        return getCollection().insertOne(customer).wasAcknowledged()
    }

    override fun findById(customerId: String): Customer? {
        return getCollection().find(eq("_id", customerId)).first()
    }

    override fun getAll(): List<Customer> {
        return getCollection().find().toList()
    }

    private fun getCollection(): MongoCollection<Customer> {
        return mongoClient.getDatabase("store")
            .getCollection("customer", Customer::class.java)
    }
}