package br.com.store.casa.codigo.controller.model.request

import br.com.store.casa.codigo.annotation.NotDuplicated
import br.com.store.casa.codigo.domain.Country
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class CountryRequest(
    @field: NotBlank
    @field: NotDuplicated(className = "Country", fieldName = "name")
    val name: String
){
    fun toDomain(): Country {
        return Country(
            name = name
        )
    }
}
