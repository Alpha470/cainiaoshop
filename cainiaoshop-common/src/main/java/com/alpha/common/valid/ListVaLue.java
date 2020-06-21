package com.alpha.common.valid;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ListValueConstraintValidator.class })
public @interface ListVaLue {

    String message() default "{com.alpha.common.valid.ListVaLue.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    int[] vals() default {};

}
