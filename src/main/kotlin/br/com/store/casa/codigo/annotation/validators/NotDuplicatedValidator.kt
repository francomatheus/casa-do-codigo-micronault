package br.com.store.casa.codigo.annotation.validators

import br.com.store.casa.codigo.annotation.NotDuplicated
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Singleton
open class NotDuplicatedValidator(
    @Inject private val entityManager: EntityManager
): ConstraintValidator<NotDuplicated, String> {

    var className: String = ""
    var fieldName: String = ""

    open override fun initialize(constraintAnnotation: NotDuplicated) {
        className = constraintAnnotation.className
        fieldName = constraintAnnotation.fieldName
    }

    @Transactional
    open override fun isValid(
        value: String?,
        context: ConstraintValidatorContext
    ): Boolean {

        val resultList= entityManager
                .createQuery("select p from $className p where p.$fieldName = :pValue")
                .setParameter("pValue", value)
                .resultList

        return resultList.isEmpty()
    }

}
