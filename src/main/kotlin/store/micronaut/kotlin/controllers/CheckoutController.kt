package store.micronaut.kotlin.controllers

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.hateoas.Link
import io.micronaut.validation.Validated
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
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
@Tag(name = "Checkout")
class CheckoutController(
    private val checkoutService: CheckoutService,
    private val checkoutRepository: CheckoutRepository
) {
    @Operation(
        summary = "Creates a checkout",
        description = "Creates a checkout and returns it. Must specify a valid customer and product."
    )
    @ApiResponses(
        ApiResponse(content = [Content(schema = Schema(implementation = Checkout::class))]),
        ApiResponse(responseCode = "422", description = "Customer or product not found"),
        ApiResponse(responseCode = "500", description = "Resource not created")
    )
    @Post("/")
    fun create(@Valid @Body createCheckoutDTO: CreateCheckoutDTO): HttpResponse<*> {
        return HttpResponse.ok(checkoutService.create(createCheckoutDTO))
    }

    @Operation(
        summary = "Search for one checkout",
        description = "Search for one checkout and returns it."
    )
    @ApiResponses(
        ApiResponse(content = [Content(schema = Schema(implementation = Checkout::class))]),
        ApiResponse(responseCode = "404", description = "Resource not found")
    )
    @Get("/{checkoutId}")
    fun findById(@PathVariable checkoutId: String): HttpResponse<Checkout?> {
        val checkout = checkoutRepository.findById(checkoutId)
        return if (checkout != null) {
            HttpResponse.ok(checkout)
        } else {
            throw ResourceNotFound()
        }
    }

    @Operation(
        summary = "Get all checkouts",
        description = "Return all checkouts on the database."
    )
    @ApiResponses(
        ApiResponse(
            content = [Content(
                array = ArraySchema(schema = Schema(implementation = Checkout::class))
            )]
        ),
    )
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