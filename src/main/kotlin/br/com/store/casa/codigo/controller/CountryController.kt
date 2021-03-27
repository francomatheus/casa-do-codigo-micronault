package br.com.store.casa.codigo.controller

import br.com.store.casa.codigo.controller.model.request.CountryRequest
import br.com.store.casa.codigo.controller.model.response.CountryResponse
import br.com.store.casa.codigo.controller.model.response.MessageError
import br.com.store.casa.codigo.repository.CountryRepository
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
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import kotlin.streams.toList

@Controller("/cdc")
@Validated
class CountryController(
    @Inject private val countryRepository: CountryRepository
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Post("/v1/countries")
    fun createCountry(@Body @Valid newCountryRequest: CountryRequest): HttpResponse<Any>{
        logger.info("class=CountryController, method=createCountry, countryRequest={} ", newCountryRequest)

        val newCountry = newCountryRequest.toDomain()

        val countrySaved = countryRepository.save(newCountry)

        return HttpResponse.ok(CountryResponse(countrySaved))
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