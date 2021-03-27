package br.com.store.casa.codigo.controller.model.request

import br.com.store.casa.codigo.annotation.NotDuplicated
import br.com.store.casa.codigo.domain.Category
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class CategoryRequest(
    @field: NotBlank
    @field: NotDuplicated(className = "Category", fieldName = "name")
    val name: String
) {
    fun toDomain(): Category {
        return Category(
            name = name
        )

    }
}