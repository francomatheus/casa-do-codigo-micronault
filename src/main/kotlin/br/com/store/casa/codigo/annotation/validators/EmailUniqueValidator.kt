package br.com.store.casa.codigo.annotation.validators

import br.com.store.casa.codigo.annotation.EmailUnique
import br.com.store.casa.codigo.repository.AuthorRepository
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmailUniqueValidator(
    @Inject val authorRepository: AuthorRepository
): ConstraintValidator<EmailUnique, String> {

    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<EmailUnique>,
        context: ConstraintValidatorContext
    ): Boolean {

        val authorByEmail = value?.let {
            authorRepository.findByEmail(value)
        }

        return !authorByEmail.isNullOrEmpty() && !authorByEmail.isEmpty()
    }

}
