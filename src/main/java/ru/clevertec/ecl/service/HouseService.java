package ru.clevertec.ecl.service;

import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.dto.requestDTO.RequestHouseDTO;
import ru.clevertec.ecl.dto.responseDTO.ResponseHouseDTO;

import java.util.UUID;

public interface HouseService extends Services<RequestHouseDTO, ResponseHouseDTO> {

    @Transactional
    UUID create(RequestHouseDTO requestHouseDTO);
}
