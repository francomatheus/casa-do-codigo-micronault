package br.com.store.casa.codigo.annotation

import br.com.store.casa.codigo.annotation.validators.NotDuplicatedValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [NotDuplicatedValidator::class])
annotation class NotDuplicated(
    val message: String = "already exist!!",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
    val className: String,
    val fieldName: String
)
