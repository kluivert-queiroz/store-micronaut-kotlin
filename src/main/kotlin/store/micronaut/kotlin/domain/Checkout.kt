package store.micronaut.kotlin.domain

import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
class Checkout {
    var id = UUID.randomUUID().toString()
    var customerId: String? = null
    var items: List<Product> = arrayListOf()
    var createdOn: Date = Date()
    var updatedOn: Date = Date()
}