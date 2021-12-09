package store.micronaut.kotlin.domain

import io.micronaut.core.annotation.Introspected
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Introspected
class Product() {
    @Schema(example = "32674e49-4ebe-4b21-92d1-5c106aa85625")
    var id: String = UUID.randomUUID().toString()

    @Schema(example = "Notebook")
    var title: String? = null

    @Schema(example = "A very good notebook for programming costs more than R$300.")
    var description: String? = null

    @Schema(example = "4000.00")
    var price: Double? = null
}