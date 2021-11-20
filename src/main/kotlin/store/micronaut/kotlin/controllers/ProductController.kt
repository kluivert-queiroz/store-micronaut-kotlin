package store.micronaut.kotlin.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import store.micronaut.kotlin.datavalidation.CreateProductDTO
import store.micronaut.kotlin.domain.Product
import store.micronaut.kotlin.exceptions.ResourceNotFound
import store.micronaut.kotlin.repositories.ProductRepository
import store.micronaut.kotlin.services.ProductService
import javax.validation.Valid

@Validated
@Controller("/product")
class ProductController(
    private val productService: ProductService,
    private val productRepository: ProductRepository
) {
    @Post("/")
    fun create(@Valid productDto: CreateProductDTO): MutableHttpResponse<Product>? {
        return HttpResponse.ok(productService.create(productDto))
    }

    @Get("/{productId}")
    fun findById(@PathVariable productId: String): HttpResponse<Product?> {
        val product = productRepository.findById(productId)
        return if (product != null) {
            HttpResponse.ok(product)
        } else {
            throw ResourceNotFound()
        }
    }

    @Get("/")
    fun findAll(): HttpResponse<List<Product>> {
        return HttpResponse.ok(productRepository.getAll())
    }
}