package store.micronaut.kotlin.exceptions

import java.lang.RuntimeException

class CustomerNotFound(var resourceId: String? = null) : RuntimeException()