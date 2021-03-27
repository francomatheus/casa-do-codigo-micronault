package br.com.store.casa.codigo.domain

import javax.persistence.*

@Entity
@Table(name = "state")
data class State(
    val name: String,
    @ManyToOne
    val country: Country
) {
    @Deprecated(message = "Just for JPA")
    constructor(): this("", Country())

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
