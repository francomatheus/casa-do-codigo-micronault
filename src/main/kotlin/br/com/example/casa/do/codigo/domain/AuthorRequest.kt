package br.com.example.casa.`do`.codigo.domain

import br.com.example.casa.`do`.codigo.domain.model.Author
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class AuthorRequest (
        @field:NotBlank
        @field:Email
        val email: String,
        @field:NotBlank
        val name: String,
        @field:NotBlank
        @field:Size(max = 400)
        val description: String
){
    fun toModel():Author{
        return Author(
                email = this.email,
                name = this.name,
                description = this.description
        )
    }
}