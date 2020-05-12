package core.model.validators;

import core.model.Sales;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SaleValidator implements Validator<Sales> {

    @Override
    public void validate(Sales entity) throws ValidatorException {
        Optional.ofNullable(entity.getId()).orElseThrow(() -> new ValidatorException("The id must not be null"));
        Optional.ofNullable(entity.getBookid()).orElseThrow(()-> new ValidatorException("Book id must not be null"));
        Optional.ofNullable(entity.getClientid()).orElseThrow(()-> new ValidatorException("Client id must not be null"));
        Optional.of(entity.getBookid()).filter((i)->i<0).ifPresent((i)->{throw new ValidatorException("Invalid value for book ID!");});
        Optional.of(entity.getClientid()).filter((i)->i<0).ifPresent((i)->{throw new ValidatorException("Invalid value for client ID!");});
    }
}