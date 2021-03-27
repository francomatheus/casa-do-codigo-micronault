package br.com.store.casa.codigo.annotation

import br.com.store.casa.codigo.annotation.validators.EmailUniqueValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [EmailUniqueValidator::class])
annotation class EmailUnique(
    val message: String = "This email already exist!!",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)
