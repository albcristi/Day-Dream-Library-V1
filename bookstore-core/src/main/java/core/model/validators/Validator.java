package core.model.validators;

import org.springframework.stereotype.Component;

@Component
public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
