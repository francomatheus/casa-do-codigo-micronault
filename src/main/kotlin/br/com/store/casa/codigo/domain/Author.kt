package br.com.store.casa.codigo.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "author")
data class Author(
        val name: String,
        val email: String,
        val description: String
) {
    constructor() : this("","","")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    val instance: LocalDateTime = LocalDateTime.now()
}
