package br.com.store.casa.codigo.controller.model.request

import br.com.store.casa.codigo.annotation.NotDuplicated
import br.com.store.casa.codigo.domain.Author
import br.com.store.casa.codigo.domain.Book
import br.com.store.casa.codigo.domain.Category
import com.fasterxml.jackson.annotation.JsonFormat
import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.EntityManager
import javax.validation.constraints.*

@Introspected
data class BookRequest(
    @field: NotBlank
    @field: NotDuplicated(className = "Book", fieldName = "title")
    val title: String,
    @field: NotBlank
    @field: Size(max = 500)
    val resume: String,
    val summary: String?,
    @field: NotNull
    @field: Positive
    @field: Min(20)
    val price: BigDecimal,
    @field: NotNull
    @field: Min(100)
    val numberOfPage: Int,
    @field: NotBlank
    @field: NotDuplicated(className = "Book", fieldName = "isbn")
    val isbn: String,
    @field: Future
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    val publicationDate: LocalDate,
    @field: NotNull
    val idCategory: Long,
    @field: NotNull
    val idAuthor: Long
) {
    fun toDomain(entityManager: EntityManager): Book {
        val author = entityManager.find(Author::class.java, idAuthor)

        val category = entityManager.find(Category::class.java, idCategory)

        return Book(
            title = title,
            resume = resume,
            summary = summary,
            price = price,
            numberOfPage = numberOfPage,
            isbn = isbn,
            publicationDate = publicationDate,
            category = category,
            author = author
        )
    }
}
