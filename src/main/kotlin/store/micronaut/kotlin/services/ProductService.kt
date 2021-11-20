package store.micronaut.kotlin.services

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import store.micronaut.kotlin.datavalidation.CreateProductDTO
import store.micronaut.kotlin.domain.Product

interface ProductService {
    fun create(productDTO: CreateProductDTO): Product
}