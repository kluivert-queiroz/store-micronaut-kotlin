package store.micronaut.kotlin.services

import store.micronaut.kotlin.datavalidation.CreateCheckoutDTO
import store.micronaut.kotlin.domain.Checkout

interface CheckoutService {
    fun create(createCheckoutDTO: CreateCheckoutDTO): Checkout
}