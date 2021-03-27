package br.com.store.casa.codigo.controller

import br.com.store.casa.codigo.controller.model.request.CategoryRequest
import br.com.store.casa.codigo.controller.model.response.CategoryResponse
import br.com.store.casa.codigo.controller.model.response.MessageError
import br.com.store.casa.codigo.repository.CategoryRepository
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
class CategoryController(
    @Inject private val categoryRepository: CategoryRepository
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Post("/v1/categories")
    fun newCategory(@Body @Valid newCategoryRequest: CategoryRequest): HttpResponse<Any>{
        logger.info("class=CategoryController, method=newCategory, message=create new category, categoryRequest={}", newCategoryRequest)

        val category = newCategoryRequest.toDomain()

        val categorySaved = categoryRepository.save(category)

        return HttpResponse.ok(CategoryResponse(categorySaved))
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