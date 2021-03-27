package br.com.store.casa.codigo.controller.model.request

import br.com.store.casa.codigo.domain.Country
import br.com.store.casa.codigo.domain.State
import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import javax.persistence.EntityManager
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
data class StateRequest(
    @field: NotBlank
    val name: String,
    @field: NotNull
    val idCountry: Long
) {
    fun toDomain(entityManager: EntityManager): State {
        val country = entityManager.find(Country::class.java, idCountry)
            ?: throw HttpStatusException(HttpStatus.NOT_FOUND, "Country not fount with id $idCountry")

        return State(
            name = name,
            country = country
        )
    }
}
