package store.micronaut.kotlin.services

import jakarta.inject.Singleton
import store.micronaut.kotlin.datavalidation.CreateCheckoutDTO
import store.micronaut.kotlin.domain.Checkout
import store.micronaut.kotlin.exceptions.CustomerNotFound
import store.micronaut.kotlin.exceptions.ProductNotFound
import store.micronaut.kotlin.exceptions.ResourceNotCreated
import store.micronaut.kotlin.exceptions.ResourceNotFound
import store.micronaut.kotlin.repositories.CheckoutRepository
import store.micronaut.kotlin.repositories.CustomerRepository
import store.micronaut.kotlin.repositories.ProductRepository
import kotlin.streams.toList

@Singleton
class CheckoutServiceImpl(
    private val checkoutRepository: CheckoutRepository,
    private val productRepository: ProductRepository,
    private val customerRepository: CustomerRepository
) : CheckoutService {
    override fun create(createCheckoutDTO: CreateCheckoutDTO): Checkout {
        if (customerRepository.findById(createCheckoutDTO.customerId.toString()) == null)
            throw CustomerNotFound(createCheckoutDTO.customerId)
        val checkout = Checkout()
        checkout.customerId = createCheckoutDTO.customerId
        checkout.items = createCheckoutDTO.items.parallelStream().map {
            productRepository.findById(it.id) ?: throw ProductNotFound(it.id)
        }.toList()
        return if (checkoutRepository.save(checkout)) {
            checkout
        } else {
            throw ResourceNotCreated()
        }
    }
}