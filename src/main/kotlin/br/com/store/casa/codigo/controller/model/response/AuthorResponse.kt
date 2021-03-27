package br.com.store.casa.codigo.controller.model.response

import br.com.store.casa.codigo.domain.Author
import java.time.LocalDateTime

class AuthorResponse(
    val name: String? = null,
    val email: String? = null,
    val description: String? = null,
    val instance: String? = null
) {
   constructor(author: Author): this(
       name = author.name,
       email = author.email,
       description = author.description,
       instance = author.instance.toString()
   )
}