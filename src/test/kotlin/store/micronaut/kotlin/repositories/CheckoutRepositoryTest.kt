package store.micronaut.kotlin.repositories

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import store.micronaut.kotlin.fixtures.CheckoutTestFixtures

@MicronautTest
class CheckoutRepositoryTest(
    private val checkoutRepository: CheckoutRepository,
) : FunSpec({
    test("should return empty list of checkouts")
    {
        checkoutRepository.getAll().size.shouldBe(0)
    }
    test("should save checkout") {
        checkoutRepository.save(CheckoutTestFixtures.checkoutDTO).shouldBeTrue()
    }
    test("should return list of checkouts with 1 item")
    {
        checkoutRepository.getAll().size.shouldBe(1)
    }
    test("should find checkout") {
        val checkout = checkoutRepository.findById(CheckoutTestFixtures.checkoutDTO.id)
        with(checkout) {
            shouldNotBeNull()
            id.shouldBe(CheckoutTestFixtures.checkoutDTO.id)
        }
    }
})