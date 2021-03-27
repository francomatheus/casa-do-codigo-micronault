package br.com.store.casa.codigo.controller.model.response

import io.micronaut.http.HttpStatus

data class MessageError(
    val instance: String,
    val status: HttpStatus,
    val message: List<String>,
    val stackTrace: String
) {

}
