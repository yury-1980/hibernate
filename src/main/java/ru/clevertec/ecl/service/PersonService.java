package ru.clevertec.ecl.service;

import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.dto.requestDTO.RequestPersonDTO;
import ru.clevertec.ecl.dto.responseDTO.ResponsePersonDTO;

import java.util.UUID;

public interface PersonService extends Services<RequestPersonDTO, ResponsePersonDTO> {

    @Transactional
    void create(RequestPersonDTO requestPersonDTO, UUID uuid);
}
