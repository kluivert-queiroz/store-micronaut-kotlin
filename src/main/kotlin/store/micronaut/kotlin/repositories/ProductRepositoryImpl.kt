package store.micronaut.kotlin.repositories

import com.mongodb.client.MongoClient
import com.mongodb.client.model.Filters.eq
import jakarta.inject.Singleton
import store.micronaut.kotlin.domain.Product

@Singleton
class ProductRepositoryImpl(
    private val mongoClient: MongoClient
) : ProductRepository {
    override fun findById(productId: String): Product? {
        return getCollection().find(eq("_id", productId)).first()
    }

    override fun getAll(): List<Product> {
        return getCollection().find().toList()
    }

    override fun save(product: Product): Boolean {
        return getCollection().insertOne(product).wasAcknowledged()
    }

    private fun getCollection(): com.mongodb.client.MongoCollection<Product> {
        return mongoClient.getDatabase("store")
            .getCollection("product", Product::class.java)
    }
}