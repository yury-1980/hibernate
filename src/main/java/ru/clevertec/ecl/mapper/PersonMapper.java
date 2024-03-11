package ru.clevertec.ecl.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.ecl.dto.requestDTO.RequestPersonDTO;
import ru.clevertec.ecl.dto.responseDTO.ResponsePersonDTO;
import ru.clevertec.ecl.entity.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    ResponsePersonDTO toResponsePersonDto(Person person);

    Person toPerson(RequestPersonDTO requestPersonDTO);
}
