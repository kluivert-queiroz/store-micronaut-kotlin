package store.micronaut.kotlin.services

import store.micronaut.kotlin.datavalidation.CreateCustomerDTO
import store.micronaut.kotlin.domain.Customer

interface CustomerService {
    fun create(createCustomerDTO: CreateCustomerDTO): Customer
}