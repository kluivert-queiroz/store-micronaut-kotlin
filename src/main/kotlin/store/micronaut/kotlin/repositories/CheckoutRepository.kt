package store.micronaut.kotlin.repositories

import store.micronaut.kotlin.domain.Checkout

interface CheckoutRepository {
    fun save(checkout: Checkout): Boolean
    fun findById(checkoutId: String): Checkout?
    fun getAll(): List<Checkout>
}