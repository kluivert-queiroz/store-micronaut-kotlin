package store.micronaut.kotlin.datavalidation

import io.micronaut.core.annotation.Introspected
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank

@Introspected
data class CreateCustomerDTO(
    @Schema(example = "John Doe")
    @field:NotBlank
    var name: String? = null,
    @field:NotBlank
    @Schema(example = "johndoe@company.com")
    var email: String? = null,
    @Schema(example = "551100000000")
    var phone: String? = null
)