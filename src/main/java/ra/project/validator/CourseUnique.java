package ra.project.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {CourseValidator.class}
)
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseUnique {
    String message() default "Tên đã tôn tại";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
