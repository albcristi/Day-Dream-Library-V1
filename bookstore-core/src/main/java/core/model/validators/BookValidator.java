package core.model.validators;


import core.model.Books;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookValidator implements Validator<Books> {

    @Override
    public void validate(Books entity) throws ValidatorException {
        Optional.ofNullable(entity.getId()).orElseThrow(() -> new ValidatorException("The id can not be null"));
        Optional.of(entity.getId()).filter((e)->e<=0).ifPresent(i->{throw new ValidatorException("Id must be >0"); });
        Optional.of(entity.getPrice()).filter((e)->e<=0).ifPresent(i->{throw new ValidatorException("Price must be >0"); });
        Optional.ofNullable(entity.getTitle()).orElseThrow(()->new ValidatorException("Title can not be null"));
        Optional.of(entity.getTitle()).filter((e)->e.equals("")).ifPresent(i->{throw new ValidatorException("Title can not be empty"); });
        Optional.ofNullable(entity.getAuthor()).orElseThrow(()->new ValidatorException("Author can not be null!"));
        Optional.of(entity.getAuthor()).filter((e)->e.equals("")).ifPresent(i->{throw new ValidatorException("Author can not be null"); });
    }


}
