package task.text_polish.validation.wordcount;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = WordCountValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WordCount {

    int max() default Integer.MAX_VALUE;

    String message() default "Content exceeds the maximum word count";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
