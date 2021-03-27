package br.com.store.casa.codigo.domain

import javax.persistence.*

@Entity
@Table(name = "country")
data class Country(
    val name: String
){
    @Deprecated(message = "Just for JPA")
    constructor():this("")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
