package store.micronaut.kotlin.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import store.micronaut.kotlin.datavalidation.CreateCustomerDTO
import store.micronaut.kotlin.domain.Customer
import store.micronaut.kotlin.exceptions.ResourceNotFound
import store.micronaut.kotlin.repositories.CustomerRepository
import store.micronaut.kotlin.services.CustomerService
import javax.validation.Valid

@Validated
@Controller("/customer")
class CustomerController(
    private val customerService: CustomerService,
    private val customerRepository: CustomerRepository
) {
    @Post("/")
    fun create(@Valid createCustomerDTOData: CreateCustomerDTO): HttpResponse<Customer?> {
        return HttpResponse.ok(customerService.create(createCustomerDTOData))
    }

    @Get("/{customerId}")
    fun findById(@PathVariable customerId: String): HttpResponse<Customer?> {
        val customer = customerRepository.findById(customerId)
        return if (customer != null) {
            HttpResponse.ok(customer)
        } else {
            throw ResourceNotFound()
        }
    }

    @Get("/")
    fun findAll(): HttpResponse<List<Customer>> {
        return HttpResponse.ok(customerRepository.getAll())
    }
}