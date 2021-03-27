package br.com.store.casa.codigo.controller

import br.com.store.casa.codigo.controller.model.request.AuthorRequest
import br.com.store.casa.codigo.controller.model.response.AuthorResponse
import br.com.store.casa.codigo.repository.AuthorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.transaction.Transactional

@Controller("/cdc")
class AuthorController(
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Inject
    lateinit var  authorRepository: AuthorRepository

    @Post("/v1/authores")
//    @Transactional
    fun newAuthor(@Body authorRequest: AuthorRequest): AuthorResponse {
        logger.info("class=AuthorController, method=newAuthor, authorRequest={} ", authorRequest)

        val author = authorRequest.toDomain()

        val authorSaved = authorRepository.save(author)

        return AuthorResponse(authorSaved)
    }
}