package store.micronaut.kotlin.controllers

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest.MicronautKotestExtension.getMock
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import store.micronaut.kotlin.domain.Checkout
import store.micronaut.kotlin.fixtures.CheckoutTestFixtures
import store.micronaut.kotlin.repositories.CheckoutRepository
import store.micronaut.kotlin.services.CheckoutService

@MicronautTest
class CheckoutControllerTest(
    private val ctx: ApplicationContext,
    private val checkoutService: CheckoutService,
    private val checkoutRepository: CheckoutRepository
) : FunSpec({
    val embeddedServer = ctx.getBean(EmbeddedServer::class.java)
    val client = embeddedServer.applicationContext.createBean(HttpClient::class.java, embeddedServer.url)
    test("should return checkout list") {
        val mockRepository = getMock(checkoutRepository)
        val checkout = Checkout()
        checkout.id = "123"
        every { mockRepository.getAll() } answers { arrayListOf(checkout) }
        val httpResponse = client.toBlocking().exchange(
            HttpRequest.GET<Class<List<Checkout>>>("/checkout"), Argument.listOf(Checkout::class.java)
        )
        httpResponse.body.shouldNotBeNull()
        httpResponse.body.get().first().id.shouldBe("123")
    }
    test("should return specified checkout") {
        val mockRepository = getMock(checkoutRepository)
        val checkout = Checkout()
        checkout.id = "123"
        every { mockRepository.findById("123") } answers { checkout }
        val httpResponse = client.toBlocking().exchange(
            HttpRequest.GET<Checkout>("/checkout/123"), Checkout::class.java
        )
        httpResponse.body.shouldNotBeNull()
        httpResponse.body.get().id.shouldBe("123")
    }
    test("should create checkout") {
        val mockService = getMock(checkoutService)
        val checkout = Checkout()
        checkout.id = "123"
        every { mockService.create(any()) } answers { checkout }
        val httpResponse = client.toBlocking().exchange(
            HttpRequest.POST("/checkout", CheckoutTestFixtures.createCheckoutJson), Checkout::class.java
        )
        httpResponse.body.shouldNotBeNull()
        httpResponse.body.get().id.shouldBe("123")
    }
}) {
    @MockBean(CheckoutService::class)
    fun checkoutService(): CheckoutService {
        return mockk()
    }

    @MockBean(CheckoutRepository::class)
    fun checkoutRepository(): CheckoutRepository {
        return mockk()
    }
}