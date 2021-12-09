package store.micronaut.kotlin.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import store.micronaut.kotlin.datavalidation.CreateCustomerDTO
import store.micronaut.kotlin.domain.Checkout
import store.micronaut.kotlin.domain.Customer
import store.micronaut.kotlin.exceptions.ResourceNotFound
import store.micronaut.kotlin.repositories.CustomerRepository
import store.micronaut.kotlin.services.CustomerService
import javax.validation.Valid

@Validated
@Controller("/customer")
@Tag(name = "Customer")
class CustomerController(
    private val customerService: CustomerService,
    private val customerRepository: CustomerRepository
) {
    @Operation(
        summary = "Creates a customer",
        description = "Creates a customer and returns it."
    )
    @ApiResponses(
        ApiResponse(content = [Content(schema = Schema(implementation = Customer::class))]),
        ApiResponse(responseCode = "500", description = "Resource not created")
    )
    @Post("/")
    fun create(@Body @Valid createCustomerDTOData: CreateCustomerDTO): HttpResponse<Customer?> {
        return HttpResponse.ok(customerService.create(createCustomerDTOData))
    }

    @Operation(
        summary = "Search for one customer",
        description = "Search for one customer and returns it."
    )
    @ApiResponses(
        ApiResponse(content = [Content(schema = Schema(implementation = Customer::class))]),
        ApiResponse(responseCode = "404", description = "Resource not found")
    )
    @Get("/{customerId}")
    fun findById(@PathVariable customerId: String): HttpResponse<Customer?> {
        val customer = customerRepository.findById(customerId)
        return if (customer != null) {
            HttpResponse.ok(customer)
        } else {
            throw ResourceNotFound()
        }
    }

    @Operation(
        summary = "Get all customers",
        description = "Return all customers on the database."
    )
    @ApiResponses(
        ApiResponse(
            content = [Content(
                array = ArraySchema(schema = Schema(implementation = Customer::class))
            )]
        ),
    )
    @Get("/")
    fun findAll(): HttpResponse<List<Customer>> {
        return HttpResponse.ok(customerRepository.getAll())
    }
}