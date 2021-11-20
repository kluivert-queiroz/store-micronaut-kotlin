package store.micronaut.kotlin.services

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import jakarta.inject.Singleton
import store.micronaut.kotlin.datavalidation.CreateProductDTO
import store.micronaut.kotlin.domain.Product
import store.micronaut.kotlin.exceptions.ResourceNotCreated
import store.micronaut.kotlin.repositories.ProductRepository

@Singleton
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {
    override fun create(productDTO: CreateProductDTO): Product {
        val product = Product()
        product.title = productDTO.title
        product.description = productDTO.description
        product.price = productDTO.price
        return if (productRepository.save(product)) product else throw ResourceNotCreated()
    }
}