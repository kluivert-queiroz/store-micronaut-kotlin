package store.micronaut.kotlin.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import store.micronaut.kotlin.datavalidation.CreateProductDTO
import store.micronaut.kotlin.domain.Checkout
import store.micronaut.kotlin.domain.Customer
import store.micronaut.kotlin.domain.Product
import store.micronaut.kotlin.exceptions.ResourceNotFound
import store.micronaut.kotlin.repositories.ProductRepository
import store.micronaut.kotlin.services.ProductService
import javax.validation.Valid

@Validated
@Controller("/product")
@Tag(name = "Product")
class ProductController(
    private val productService: ProductService,
    private val productRepository: ProductRepository
) {
    @Operation(
        summary = "Creates a product",
        description = "Creates a product and returns it."
    )
    @ApiResponses(
        ApiResponse(content = [Content(schema = Schema(implementation = Product::class))]),
        ApiResponse(responseCode = "500", description = "Resource not created")
    )
    @Post("/")
    fun create(@Body @Valid productDto: CreateProductDTO): MutableHttpResponse<Product>? {
        return HttpResponse.ok(productService.create(productDto))
    }

    @Operation(
        summary = "Search for one customer",
        description = "Search for one customer and returns it."
    )
    @ApiResponses(
        ApiResponse(content = [Content(schema = Schema(implementation = Product::class))]),
        ApiResponse(responseCode = "404", description = "Resource not found")
    )
    @Get("/{productId}")
    fun findById(@PathVariable productId: String): HttpResponse<Product?> {
        val product = productRepository.findById(productId)
        return if (product != null) {
            HttpResponse.ok(product)
        } else {
            throw ResourceNotFound()
        }
    }

    @Operation(
        summary = "Get all products",
        description = "Return all products on the database."
    )
    @ApiResponses(
        ApiResponse(
            content = [Content(
                array = ArraySchema(schema = Schema(implementation = Product::class))
            )]
        ),
    )
    @Get("/")
    fun findAll(): HttpResponse<List<Product>> {
        return HttpResponse.ok(productRepository.getAll())
    }
}