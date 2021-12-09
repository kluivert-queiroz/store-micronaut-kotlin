package store.micronaut.kotlin.datavalidation

import io.micronaut.core.annotation.Introspected
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class CreateCheckoutDTO(
    @Schema(example = "563adf71-5f5e-4bdb-bf23-be3b2d04e4a0")
    @field:NotBlank
    var customerId: String? = null,
    @field:Valid @field:Size(min = 1)
    var items: List<CheckoutItem>
)

class CheckoutItem(
    @Schema(example = "d375610c-82d8-432a-8805-8db5c0bbf9cc") @field:NotBlank var id: String,
    @Schema(example = "1") var quantity: Int? = 1
)