package store.micronaut.kotlin.repositories

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import store.micronaut.kotlin.domain.Product

interface ProductRepository {
    fun findById(productId: String): Product?
    fun getAll(): List<Product>
    fun save(product: Product): Boolean
}