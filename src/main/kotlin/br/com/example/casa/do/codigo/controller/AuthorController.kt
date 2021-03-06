package br.com.example.casa.`do`.codigo.controller

import br.com.example.casa.`do`.codigo.domain.AuthorRequest
import br.com.example.casa.`do`.codigo.domain.response.AuthorResponse
import br.com.example.casa.`do`.codigo.repository.AuthorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.slf4j.LoggerFactory
import javax.transaction.Transactional
import javax.validation.Valid

@Controller("/cdc/v1/autores")
class AuthorController(
        private val authorRepository: AuthorRepository
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Post
    @Transactional
    fun createAuthor(@Valid @Body authorRequest: AuthorRequest): HttpResponse<AuthorResponse>{
        logger.info("Request received for create a new author. authorRequest={}",
            authorRequest
        )

        val author = authorRequest.toModel()
        authorRepository.save(author)

        return HttpResponse.created(author.toResponse())
    }
}