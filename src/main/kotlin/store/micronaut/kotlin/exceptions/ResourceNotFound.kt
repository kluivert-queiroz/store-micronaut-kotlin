package store.micronaut.kotlin.exceptions

import java.lang.RuntimeException

class ResourceNotFound(var resourceId: String? = null) : RuntimeException()