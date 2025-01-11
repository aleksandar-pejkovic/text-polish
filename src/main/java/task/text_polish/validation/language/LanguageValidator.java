package task.text_polish.validation.language;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import task.text_polish.service.CacheService;

@Component
public class LanguageValidator implements ConstraintValidator<ValidLanguage, String> {

    private final CacheService cacheService;

    public LanguageValidator(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return cacheService.getSupportedLanguages().contains(value);
    }
}
