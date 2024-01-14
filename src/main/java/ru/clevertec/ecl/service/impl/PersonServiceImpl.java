package ru.clevertec.ecl.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.ecl.dto.requestDTO.RequestPersonDTO;
import ru.clevertec.ecl.dto.responseDTO.ResponsePersonDTO;
import ru.clevertec.ecl.entity.Person;
import ru.clevertec.ecl.mapper.PersonMapper;
import ru.clevertec.ecl.repository.Repository;
import ru.clevertec.ecl.service.PersonService;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private Repository<Person> repository;
    private PersonMapper mapper;

    @Override
    public List<ResponsePersonDTO> findByAll(int pageNumber, int pageSize) {
        return repository.findByAll(pageNumber, pageSize).stream()
                .map(person -> mapper.toResponsePersonDto((Person) person))
                .toList();
    }

    @Override
    public ResponsePersonDTO findByUUID(UUID uuid) throws Throwable {
        return repository.findByUUID(uuid)
                .map(person -> mapper.toResponsePersonDto((Person) person))
                .orElseThrow(null);// TODO: 14-01-2024: дописать.
    }

    @Override
    public UUID create(RequestPersonDTO requestPersonDTO) {
        Person person = mapper.toPerson(requestPersonDTO);

        return repository.create(person);
    }

    @Override
    public void update(RequestPersonDTO person) {

    }

    @Override
    public void delete(UUID uuid) {

    }
}
