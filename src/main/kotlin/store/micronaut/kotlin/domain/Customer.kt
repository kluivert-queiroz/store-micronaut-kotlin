package store.micronaut.kotlin.domain

import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotBlank

@Introspected
class Customer {
    var id: String? = UUID.randomUUID().toString()
    var name: String? = null
    var email: String? = null
    var phone: String? = null
}