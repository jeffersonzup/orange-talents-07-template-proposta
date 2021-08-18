package br.com.zupacademy.jefferson.microservicepropostas.validation;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfOuCnpjValidator implements ConstraintValidator<CpfOuCnpj, CharSequence> {

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        //é uma convenção da BeanValidation deixar sua validação específica
        if(value==null) {
            return true;
        }

        CNPJValidator cnpjValidator = new CNPJValidator();
        CPFValidator cpfValidator = new CPFValidator();

        cnpjValidator.initialize(null);
        cpfValidator.initialize(null);

        return cnpjValidator.isValid(value, context) || cpfValidator.isValid(value, context);
    }
}
