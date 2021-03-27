package br.com.store.casa.codigo.repository

import br.com.store.casa.codigo.domain.Book
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface BookRepository : JpaRepository<Book, Long>