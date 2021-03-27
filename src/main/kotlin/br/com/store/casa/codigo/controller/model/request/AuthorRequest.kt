package br.com.store.casa.codigo.controller.model.request

import br.com.store.casa.codigo.annotation.EmailUnique
import br.com.store.casa.codigo.domain.Author
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * Validation
 * https://docs.micronaut.io/2.0.0.M2/guide/index.html#beanValidation
 */

@Introspected
data class AuthorRequest(
    @field: NotBlank
    val name: String,
    @field: NotBlank
    @field: Email
    @field: EmailUnique
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
