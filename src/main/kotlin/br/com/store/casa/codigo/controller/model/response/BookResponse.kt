package br.com.store.casa.codigo.controller.model.response

import br.com.store.casa.codigo.domain.Author
import br.com.store.casa.codigo.domain.Book
import br.com.store.casa.codigo.domain.Category
import java.math.BigDecimal
import java.time.LocalDate

data class BookResponse(
    val title: String,
    val resume: String,
    val summary: Any?,
    val price: BigDecimal,
    val numberOfPage: Int,
    val isbn: String,
    val publicationDate: LocalDate,
    val category: Category,
    val author: Author
) {
    constructor(newBook: Book) : this(
        title = newBook.title,
        resume = newBook.resume,
        summary = newBook.summary,
        price = newBook.price,
        numberOfPage = newBook.numberOfPage,
        isbn = newBook.isbn,
        publicationDate = newBook.publicationDate,
        category = newBook.category,
        author = newBook.author
    )
}
