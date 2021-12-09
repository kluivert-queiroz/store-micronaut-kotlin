package store.micronaut.kotlin.domain

import io.micronaut.core.annotation.Introspected
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*


@Introspected
class Checkout {
    var id: String? = UUID.randomUUID().toString()
    var customerId: String? = null
    var items: List<Product>? = arrayListOf()
    var createdOn: Date = Date()
    var updatedOn: Date = Date()
}