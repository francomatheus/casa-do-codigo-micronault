package br.com.store.casa.codigo.domain

import javax.persistence.*

@Entity
@Table(name = "category")
data class Category(
    val name: String
) {
    @Deprecated(message = "Just for JPA")
    constructor() : this("")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}