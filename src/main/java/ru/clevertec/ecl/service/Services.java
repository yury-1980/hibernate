package ru.clevertec.ecl.service;

import java.util.List;
import java.util.UUID;

public interface Services<T, U> {

    List<U> findByAll(int pageNumber, int pageSize);

    U findByUUID(UUID uuid) throws Throwable;

    UUID create(T t);

    void update(T t);

    void delete(UUID uuid);
}
