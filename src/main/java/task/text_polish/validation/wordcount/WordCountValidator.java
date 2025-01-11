package task.text_polish.validation.wordcount;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WordCountValidator implements ConstraintValidator<WordCount, String> {

    private int max;

    @Override
    public void initialize(WordCount constraintAnnotation) {
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        int wordCount = value.trim().split("\\s+").length;
        return wordCount <= max;
    }
}
