package store.micronaut.kotlin.exceptions

import java.lang.RuntimeException

class ProductNotFound(var resourceId: String? = null) : RuntimeException()