package store.micronaut.kotlin

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("store.micronaut.kotlin")
		.start()
}

