package br.com.store.casa.codigo.controller.model.response

import br.com.store.casa.codigo.domain.Category

data class CategoryResponse(
    val name: String
) {

    constructor(category: Category): this(
        name = category.name
    )
}
