package br.com.example.casa.`do`.codigo.domain.model

import br.com.example.casa.`do`.codigo.domain.response.AuthorResponse
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Author(
        val email: String,
        val name: String,
        val description: String
) {

    @Id
    @GeneratedValue
    var id: Long? = null;

    fun toResponse(): AuthorResponse {
        return AuthorResponse(
                this.id,
                this.name,
                this.email,
                this.description
        )
    }

}
