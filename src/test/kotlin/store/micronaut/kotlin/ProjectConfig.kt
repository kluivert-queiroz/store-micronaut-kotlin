package store.micronaut.kotlin

import io.kotest.core.config.AbstractProjectConfig
import io.micronaut.test.extensions.kotest.MicronautKotestExtension
import store.micronaut.kotlin.utils.TestDbContainer

object ProjectConfig : AbstractProjectConfig() {
    override fun listeners() = listOf(MicronautKotestExtension)
    override fun extensions() = listOf(MicronautKotestExtension)
    override fun beforeAll() {
        TestDbContainer.start()
    }

    override fun afterAll() {
        TestDbContainer.stop()
    }
}