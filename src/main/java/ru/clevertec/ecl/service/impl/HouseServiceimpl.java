package ru.clevertec.ecl.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.ecl.dto.requestDTO.RequestHouseDTO;
import ru.clevertec.ecl.dto.responseDTO.ResponseHouseDTO;
import ru.clevertec.ecl.entity.House;
import ru.clevertec.ecl.mapper.HouseMapper;
import ru.clevertec.ecl.repository.Repository;
import ru.clevertec.ecl.service.HouseService;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HouseServiceimpl implements HouseService {

    private Repository<House> repository;
    private HouseMapper mapper;

    @Override
    public List<ResponseHouseDTO> findByAll(int pageNumber, int pageSize) {
        return repository.findByAll(pageNumber, pageSize).stream()
                .map(house -> mapper.toResponseHouseDTO((House) house))
                .toList();
    }

    @Override
    public ResponseHouseDTO findByUUID(UUID uuid) throws Throwable {
        return repository.findByUUID(uuid)
                .map(house -> mapper.toResponseHouseDTO((House) house))
                .orElseThrow(null);// TODO: 14-01-2024: дописать.
    }

    @Override
    public UUID create(RequestHouseDTO requestHouseDTO) {
        House house = mapper.toHouse(requestHouseDTO);

        return repository.create(house);
    }

    @Override
    public void update(RequestHouseDTO requestHouseDTO) {

    }

    @Override
    public void delete(UUID uuid) {

    }
}
