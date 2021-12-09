package store.micronaut.kotlin.services

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest.MicronautKotestExtension.getMock
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import store.micronaut.kotlin.datavalidation.CheckoutItem
import store.micronaut.kotlin.datavalidation.CreateCheckoutDTO
import store.micronaut.kotlin.domain.Customer
import store.micronaut.kotlin.domain.Product
import store.micronaut.kotlin.exceptions.CustomerNotFound
import store.micronaut.kotlin.exceptions.ProductNotFound
import store.micronaut.kotlin.exceptions.ResourceNotCreated
import store.micronaut.kotlin.repositories.CheckoutRepository
import store.micronaut.kotlin.repositories.CustomerRepository
import store.micronaut.kotlin.repositories.ProductRepository

@MicronautTest
class CheckoutServiceTest(
    private val checkoutRepository: CheckoutRepository,
    private val productRepository: ProductRepository,
    private val customerRepository: CustomerRepository,
    private val checkoutService: CheckoutService
) : FunSpec({
    test("should create checkout") {
        val mockCustomerRepository = getMock(customerRepository)
        val mockCheckoutRepository = getMock(checkoutRepository)
        val mockProductRepository = getMock(productRepository)
        every { mockCustomerRepository.findById("123") } returns Customer()
        every { mockProductRepository.findById(any()) } returns mockkClass(Product::class, relaxed = true)
        every { mockCheckoutRepository.save(any()) } returns true
        val createCheckoutDTO = CreateCheckoutDTO("123", listOf(CheckoutItem("123", 1)))
        checkoutService.create(createCheckoutDTO).shouldNotBeNull()
    }
    test("should throw for invalid customer") {
        val mockCustomerRepository = getMock(customerRepository)
        every { mockCustomerRepository.findById("123") } returns null
        val createCheckoutDTO = CreateCheckoutDTO("123", listOf(CheckoutItem("123", 1)))
        shouldThrow<CustomerNotFound> {
            checkoutService.create(createCheckoutDTO).shouldNotBeNull()
        }
    }
    test("should throw for invalid product") {
        val mockCustomerRepository = getMock(customerRepository)
        val mockProductRepository = getMock(productRepository)
        every { mockCustomerRepository.findById("123") } returns Customer()
        every { mockProductRepository.findById("123") } returns null
        val createCheckoutDTO = CreateCheckoutDTO("123", listOf(CheckoutItem("123", 1)))
        shouldThrow<ProductNotFound> {
            checkoutService.create(createCheckoutDTO).shouldNotBeNull()
        }
    }
    test("should throw when creation fails") {
        val mockCustomerRepository = getMock(customerRepository)
        val mockCheckoutRepository = getMock(checkoutRepository)
        val mockProductRepository = getMock(productRepository)
        every { mockCustomerRepository.findById("123") } returns Customer()
        every { mockProductRepository.findById(any()) } returns mockkClass(Product::class, relaxed = true)
        every { mockCheckoutRepository.save(any()) } returns false
        val createCheckoutDTO = CreateCheckoutDTO("123", listOf(CheckoutItem("123", 1)))
        shouldThrow<ResourceNotCreated> {
            checkoutService.create(createCheckoutDTO)
        }
    }
}) {
    @MockBean(CheckoutRepository::class)
    fun checkoutRepository(): CheckoutRepository {
        return mockk()
    }

    @MockBean(ProductRepository::class)
    fun productRepository(): ProductRepository {
        return mockk()
    }

    @MockBean(CustomerRepository::class)
    fun customerRepository(): CustomerRepository {
        return mockk()
    }
}