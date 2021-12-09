package store.micronaut.kotlin.configurations

import io.micronaut.context.annotation.ConfigurationProperties
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@ConfigurationProperties("store")
interface StoreConfiguration {
    @get:NotNull
    @get:NotBlank
    val database: String
    interface Database{
        val host: String
        val port: String
    }
    val databaseConnection: Database
}