package br.com.store.casa.codigo.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "author")
data class Author(
        val name: String,
        @Column(unique = true)
        val email: String,
        val description: String
) {
    @Deprecated(message = "Just for JPA")
    constructor() : this("","","")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    val instance: LocalDateTime = LocalDateTime.now()
}
