package br.com.store.casa.codigo

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("br.com.store.casa.codigo")
		.start()
}

