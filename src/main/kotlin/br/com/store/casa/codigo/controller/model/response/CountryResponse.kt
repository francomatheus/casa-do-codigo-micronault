package br.com.store.casa.codigo.controller.model.response

import br.com.store.casa.codigo.domain.Country

data class CountryResponse(
    val name: String
) {

    constructor(countrySaved: Country): this(
        name = countrySaved.name
    )
}
