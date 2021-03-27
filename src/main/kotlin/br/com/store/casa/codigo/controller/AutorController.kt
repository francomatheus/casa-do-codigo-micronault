package br.com.store.casa.codigo.controller

import br.com.store.casa.codigo.controller.model.request.AuthorRequest
import br.com.store.casa.codigo.controller.model.response.AuthorResponse
import br.com.store.casa.codigo.controller.model.response.MessageError
import br.com.store.casa.codigo.repository.AuthorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import kotlin.streams.toList

const val ONE = 1.toInt()

@Controller("/cdc")
@Validated
class AuthorController(
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Inject
    lateinit var  authorRepository: AuthorRepository

    @Post("/v1/authores")
    @Transactional
    fun newAuthor(@Body @Valid authorRequest: AuthorRequest): HttpResponse<AuthorResponse>? {
        logger.info("class=AuthorController, method=newAuthor, authorRequest={} ", authorRequest)

        val author = authorRequest.toDomain()

        val authorSaved = authorRepository.save(author)

        return HttpResponse.ok(AuthorResponse(authorSaved))
    }

    @Error(exception = ConstraintViolationException::class)
    fun handlerConstraints(e: ConstraintViolationException): HttpResponse<Any> {
        logger.info("message=ValidationError")

        val toList = e.constraintViolations.stream().map {
            val path = it.propertyPath.toString().split(".")
            val lastItemOfList = path.size - ONE
            val field = path.get(lastItemOfList)

            "Field $field ${it.message}"
        }.toList()
        val messageError = MessageError(
            LocalDateTime.now().toString(),
            HttpStatus.BAD_REQUEST,
            toList,
            e.cause.toString()
        )
        return HttpResponse.badRequest(messageError)
    }
}