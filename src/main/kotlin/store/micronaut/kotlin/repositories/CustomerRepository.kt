package store.micronaut.kotlin.repositories

import store.micronaut.kotlin.domain.Customer

interface CustomerRepository {
    fun save(customer: Customer): Boolean
    fun findById(customerId: String): Customer?
    fun getAll(): List<Customer>
}