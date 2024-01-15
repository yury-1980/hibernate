package ru.clevertec.ecl.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.ecl.dto.requestDTO.RequestPersonDTO;
import ru.clevertec.ecl.dto.responseDTO.ResponsePersonDTO;
import ru.clevertec.ecl.entity.Person;
import ru.clevertec.ecl.mapper.PersonMapper;
import ru.clevertec.ecl.repository.Repository;
import ru.clevertec.ecl.service.PersonService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    public void update(RequestPersonDTO requestPersonDTO, UUID uuid) {
        Optional<Person> byUUID = repository.findByUUID(uuid);

        try {

            if (byUUID.isPresent()) {
                Person person = byUUID.get();
                person.setName(requestPersonDTO.getName());
                person.setSurname(requestPersonDTO.getSurname());
                person.setSex(requestPersonDTO.getSex());
                person.setPassportSeries(requestPersonDTO.getPassportSeries());
                person.setPassportNumber(requestPersonDTO.getPassportNumber());
                person.setUpdateDate(LocalDateTime.now());

                repository.update(person);
            } else {
                throw new Exception("Object Empty!");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(UUID uuid) {
        repository.delete(uuid);
    }
}
