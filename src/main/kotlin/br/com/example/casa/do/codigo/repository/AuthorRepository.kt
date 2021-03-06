package br.com.example.casa.`do`.codigo.repository

import br.com.example.casa.`do`.codigo.domain.model.Author
import io.micronaut.data.jpa.repository.JpaRepository

interface AuthorRepository: JpaRepository<Author, Long> {
}