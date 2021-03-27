package br.com.store.casa.codigo.controller

import br.com.store.casa.codigo.controller.model.request.StateRequest
import br.com.store.casa.codigo.controller.model.response.MessageError
import br.com.store.casa.codigo.controller.model.response.StateResponse
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
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import kotlin.streams.toList

@Controller("/cdc")
@Validated
open class StateController(
    @Inject private val entityManager: EntityManager
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Post("/v1/states")
    @Transactional
    open fun createState(@Body @Valid newStateRequest: StateRequest): HttpResponse<Any>{
        logger.info("class=StateController, method=createState, stateRequest={} ", newStateRequest)

        val newState = newStateRequest.toDomain(entityManager)

        val stateSaved = entityManager.persist(newState)

        return HttpResponse.ok(StateResponse(newState))
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