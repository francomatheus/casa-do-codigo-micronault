package br.com.store.casa.codigo.domain

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

/**
 * mapeamento: https://micronaut-projects.github.io/micronaut-data/latest/guide/#sqlMapping
 */

@Entity
@Table(name = "book")
class Book(
    val title: String,
    val resume: String,
    val summary: String?,
    val price: BigDecimal,
    val numberOfPage: Int,
    val isbn: String,
    val publicationDate: LocalDate,
    @ManyToOne
    val category: Category,
    @ManyToOne
    val author: Author
) {
    @Deprecated(message = "Just for JPA")
    constructor() : this(
        "", "","", BigDecimal.ZERO,0.toInt(),"", LocalDate.now(),Category(),Author()
    ) {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
