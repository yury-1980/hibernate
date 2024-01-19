package ru.clevertec.ecl.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.dto.requestDTO.RequestPersonDTO;
import ru.clevertec.ecl.dto.responseDTO.ResponsePersonDTO;
import ru.clevertec.ecl.entity.Person;
import ru.clevertec.ecl.mapper.PersonMapper;
import ru.clevertec.ecl.repository.Repository;
import ru.clevertec.ecl.service.PersonService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
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
    @Transactional
    public UUID create(RequestPersonDTO requestPersonDTO) {
        Person person = mapper.toPerson(requestPersonDTO);
        person.setUuid(UUID.randomUUID());
        person.setCreateDate(LocalDateTime.now());
        person.setUpdateDate(person.getCreateDate());

        return repository.create(person);
    }

    @Override
    @Transactional
    public void update(RequestPersonDTO requestPersonDTO, UUID uuid) {
        repository.findByUUID(uuid).ifPresent(person -> {
            person.setName(requestPersonDTO.getName());
            person.setSurname(requestPersonDTO.getSurname());
            person.setSex(requestPersonDTO.getSex());
            person.setPassportSeries(requestPersonDTO.getPassportSeries());
            person.setPassportNumber(requestPersonDTO.getPassportNumber());
            person.setUpdateDate(LocalDateTime.now());

            repository.update(person);
        });

    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        repository.delete(uuid);
    }
}
