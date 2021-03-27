package br.com.store.casa.codigo.controller.model.response

import br.com.store.casa.codigo.domain.Country
import br.com.store.casa.codigo.domain.State

data class StateResponse(
    val name: String,
    val country: Country
) {

    constructor(state: State) : this(
        name = state.name,
        country = state.country
    )

}
