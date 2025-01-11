package task.text_polish.validation.domain;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import task.text_polish.service.CacheService;

@Component
public class DomainValidator implements ConstraintValidator<ValidDomain, String> {

    private final CacheService cacheService;

    public DomainValidator(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return cacheService.getSupportedDomains().contains(value);
    }
}
