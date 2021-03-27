package br.com.store.casa.codigo.controller

import br.com.store.casa.codigo.controller.model.request.BookRequest
import br.com.store.casa.codigo.controller.model.response.BookResponse
import br.com.store.casa.codigo.controller.model.response.MessageError
import br.com.store.casa.codigo.domain.Book
import br.com.store.casa.codigo.repository.BookRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import kotlin.streams.toList

@Controller("/cdc")
@Validated
open class BookController(
    @Inject private val entityManager: EntityManager
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Get("/v1/books")
    @Transactional
    open fun listAllBooks(): HttpResponse<Any>{
        val resultList = entityManager.createQuery("select b from Book b", Book::class.java)
            .resultList
            .stream()
            .map {
                BookResponse(it)
            }.toList()

        return HttpResponse.ok(resultList)
    }

    @Get("/v1/books/{idBook}")
    @Transactional
    open fun detailsBook(@PathVariable idBook: Long): HttpResponse<Any> {
        val detailsBook = entityManager.find(Book::class.java, idBook)
        if (detailsBook == null){
            return HttpResponse.notFound("id $idBook not found")
        }

        return HttpResponse.ok(BookResponse(detailsBook))
    }

    @Post("/v1/books")
    @Transactional
    open fun newBook(@Body @Valid newBookRequest: BookRequest): HttpResponse<Any>{
        logger.info("class=BookController, method=newBook, message=create a new book, bookRequest={} ", newBookRequest)

        val newBook = newBookRequest.toDomain(entityManager)

        entityManager.persist(newBook)

        return HttpResponse.ok(BookResponse(newBook))
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
            e.stackTraceToString()
        )
        return HttpResponse.badRequest(messageError)
    }
}