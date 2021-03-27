package br.com.store.casa.codigo.repository

import br.com.store.casa.codigo.domain.Category
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface CategoryRepository : JpaRepository<Category, Long>
