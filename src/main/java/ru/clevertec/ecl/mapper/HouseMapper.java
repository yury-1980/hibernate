package ru.clevertec.ecl.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.ecl.dto.requestDTO.RequestHouseDTO;
import ru.clevertec.ecl.dto.responseDTO.ResponseHouseDTO;
import ru.clevertec.ecl.entity.House;

@Mapper(componentModel = "spring")
public interface HouseMapper {

    ResponseHouseDTO toResponseHouseDTO(House house);

    House toHouse(RequestHouseDTO requestHouseDTO);
}
