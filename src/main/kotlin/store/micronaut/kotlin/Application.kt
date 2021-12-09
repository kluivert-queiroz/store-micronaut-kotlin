package store.micronaut.kotlin

import io.micronaut.runtime.Micronaut.*
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
    info = Info(
        title = "StoreFront API",
        version = "0.0",
        description = "This is the swagger for storefront sample app.",
    )
)
object Application

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("store.micronaut.kotlin")
        .start()
}



