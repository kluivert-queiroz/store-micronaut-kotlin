package store.micronaut.kotlin.domain

import io.micronaut.core.annotation.Introspected
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*
import javax.validation.constraints.NotBlank

@Introspected
class Customer {
    @Schema(example = "defce631-5ab7-4cb8-9470-fe49474c79c7")
    var id: String? = UUID.randomUUID().toString()
    @Schema(example = "John Doe")
    var name: String? = null
    @Schema(example = "johndoe@company.com")
    var email: String? = null
    @Schema(example = "551100000000")
    var phone: String? = null
}