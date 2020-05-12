package web.converter;
import core.model.Clients;
import org.springframework.stereotype.Component;
import web.dto.ClientDto;

@Component
public class ClientConverter extends BaseConverter<Clients, ClientDto> {
    @Override
    public Clients convertDtoToModel(ClientDto dtoObject) {
        Clients client = Clients.builder()
                .name(dtoObject.getName())
                .moneySpent(dtoObject.getMoneySpent())
                .build();
        client.setId(dtoObject.getId());
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Clients modelObject) {
        ClientDto clientDto = ClientDto.builder()
                .name(modelObject.getName())
                .moneySpent(modelObject.getMoneySpent())
                .build();
        clientDto.setId(modelObject.getId());
        return clientDto;
    }
}
