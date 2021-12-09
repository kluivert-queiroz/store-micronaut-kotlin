package store.micronaut.kotlin.fixtures

import store.micronaut.kotlin.domain.Checkout

class CheckoutTestFixtures {
    companion object {
        val createCheckoutJson =
            CheckoutTestFixtures::class.java.getResource("/mocks/create-checkout.json").readText()!!
        val checkoutDTO = Checkout()
    }
}