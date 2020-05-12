package web.converter;

import core.model.BaseEntity;
import web.dto.BaseDto;

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {
    Model convertDtoToModel(Dto dtoObject);

    Dto convertModelToDto(Model modelObject);
}
