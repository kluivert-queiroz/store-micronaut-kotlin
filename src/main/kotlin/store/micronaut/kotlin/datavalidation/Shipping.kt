package store.micronaut.kotlin.datavalidation

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
class Shipping {
    @NotBlank
    var country: String? = null

    @NotBlank
    var state: String? = null

    @NotBlank
    var city: String? = null

    @NotBlank
    var street: String? = null

    @NotBlank
    var streetNumber: String? = null

    @NotBlank
    var zipCode: String? = null
}