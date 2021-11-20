package store.micronaut.kotlin.controllers

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.hateoas.Link
import io.micronaut.validation.Validated
import store.micronaut.kotlin.datavalidation.CreateCheckoutDTO
import store.micronaut.kotlin.domain.Checkout
import store.micronaut.kotlin.exceptions.CustomerNotFound
import store.micronaut.kotlin.exceptions.ProductNotFound
import store.micronaut.kotlin.exceptions.ResourceNotFound
import store.micronaut.kotlin.repositories.CheckoutRepository
import store.micronaut.kotlin.services.CheckoutService
import javax.validation.Valid

@Validated
@Controller("/checkout")
class CheckoutController(
    private val checkoutService: CheckoutService,
    private val checkoutRepository: CheckoutRepository
) {
    @Post("/")
    fun create(@Valid @Body createCheckoutDTO: CreateCheckoutDTO): HttpResponse<*> {
        return HttpResponse.ok(checkoutService.create(createCheckoutDTO))
    }

    @Get("/{checkoutId}")
    fun findById(@PathVariable checkoutId: String): HttpResponse<Checkout?> {
        val checkout = checkoutRepository.findById(checkoutId)
        return if (checkout != null) {
            HttpResponse.ok(checkout)
        } else {
            throw ResourceNotFound()
        }
    }

    @Get("/")
    fun findAll(): HttpResponse<List<Checkout>> {
        return HttpResponse.ok(checkoutRepository.getAll())
    }

    @Error
    fun customerNotFoundError(request: HttpRequest<*>, e: CustomerNotFound): HttpResponse<JsonError> {
        val error = JsonError("Specified customer could not be found.")
            .link(Link.SELF, Link.of(request.uri))
        return HttpResponse.status<JsonError>(
            HttpStatus.UNPROCESSABLE_ENTITY
        ).body(error)
    }

    @Error
    fun productNotFoundError(request: HttpRequest<*>, e: ProductNotFound): HttpResponse<JsonError> {
        val error = JsonError("Product of id \"${e.resourceId}\" could not be found.")
            .link(Link.SELF, Link.of(request.uri))
        return HttpResponse.status<JsonError>(
            HttpStatus.UNPROCESSABLE_ENTITY
        ).body(error)
    }
}