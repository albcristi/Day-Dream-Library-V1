package web.converter;
import core.model.Sales;
import org.springframework.stereotype.Component;
import web.dto.SaleDto;

@Component
public class SaleConverter extends BaseConverter<Sales, SaleDto> {
    @Override
    public Sales convertDtoToModel(SaleDto dtoObject) {
        Sales  sale = Sales.builder()
                .bookid(dtoObject.getBookid())
                .clientid(dtoObject.getClientid())
                .build();
        sale.setId(dtoObject.getId());
        return sale;
    }

    @Override
    public SaleDto convertModelToDto(Sales modelObject) {
        SaleDto saleDto = SaleDto.builder()
                .bookid(modelObject.getBookid())
                .clientid(modelObject.getClientid())
                .build();
        saleDto.setId(modelObject.getId());
        return saleDto;
    }
}
