package br.com.store.casa.codigo.controller.model.request

import br.com.store.casa.codigo.domain.Author
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class AuthorRequest(
    @field: NotBlank
    val name: String,
    @field: NotBlank
    @field: Email
    val email: String,
    @field: NotBlank
    @field: Size(max = 400)
    val description: String
) {

    fun toDomain(): Author {
        return Author(
            name = name,
            email = email,
            description = description
        )
    }
}
