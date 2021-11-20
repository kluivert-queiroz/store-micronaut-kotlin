package store.micronaut.kotlin.datavalidation

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class CreateCustomerDTO(
    @field:NotBlank
    var name: String? = null,
    @field:NotBlank
    var email: String? = null,
    var phone: String? = null
)