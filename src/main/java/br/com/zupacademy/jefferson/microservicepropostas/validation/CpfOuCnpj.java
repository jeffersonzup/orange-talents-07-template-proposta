package br.com.zupacademy.jefferson.microservicepropostas.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CpfOuCnpjValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfOuCnpj {

    String message() default "insira um CPF ou CNPJ válido.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
