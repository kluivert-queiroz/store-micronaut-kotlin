package store.micronaut.kotlin.datavalidation

import io.micronaut.core.annotation.Introspected
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class CreateCheckoutDTO(
    @field:NotBlank
    var customerId: String? = null,
    @field:Valid @field:Size(min=1)
    var items: List<CheckoutItem>
)

class CheckoutItem(@field:NotBlank var id: String, var quantity: Int? = 1)