package store.micronaut.kotlin.domain

import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
class Product() {
    var id: String = UUID.randomUUID().toString()
    var title: String? = null
    var description: String? = null
    var price: Double? = null
}