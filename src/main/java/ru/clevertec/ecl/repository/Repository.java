package ru.clevertec.ecl.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Repository<T> {

    List<T> findByAll(int pageNumber, int pageSize);

    Optional<T> findByUUID(UUID uuid);

    UUID create(T t);

    void update(T t);

    void delete(UUID uuid);
}
