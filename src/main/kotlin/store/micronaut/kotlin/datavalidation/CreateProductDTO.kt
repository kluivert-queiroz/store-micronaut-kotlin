package store.micronaut.kotlin.datavalidation

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Introspected
data class CreateProductDTO(
    @field:NotBlank var title: String?,
    @field:NotBlank var description: String?,
    @field:NotBlank
    @field:Min(0)
    var price: Double?
)