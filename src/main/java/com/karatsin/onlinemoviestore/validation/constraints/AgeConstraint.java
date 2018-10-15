package com.karatsin.onlinemoviestore.validation.constraints;


import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { AgeValidator.class  })
public @interface AgeConstraint {
	
    String message() default "{com.karatsin.onlinemoviestore.validation.constraints.AgeConstraint.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    long minAge();
 
}