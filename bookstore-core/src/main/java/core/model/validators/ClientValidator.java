package core.model.validators;

import core.model.Clients;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientValidator implements Validator<Clients> {


    @Override
    public void validate(Clients entity) throws ValidatorException {
        Optional.ofNullable(entity.getId()).orElseThrow(()-> new ValidatorException("ID can not be null!"));
        Optional.of(entity.getId()).filter((i)->i<0).ifPresent((i)->{throw new ValidatorException("Invalid value for ID!");});

        Optional.ofNullable(entity.getName()).orElseThrow(()->new ValidatorException("Client name can not be null!"));
        Optional.of(entity.getName()).filter((e)->e.matches(".*[,?/!:;0-9].*")).ifPresent(i->{throw new ValidatorException("Name contains invalid characters!");});

        Optional.of(entity.getMoneySpent()).filter((e)->e<0).ifPresent(i->{throw new ValidatorException("Invalid value for money!");});
    }


}