package ru.clevertec.ecl.service;

import java.util.List;
import java.util.UUID;

public interface Services<T, U> {

    List<U> findByAll();

    U findByUUID(UUID uuid) throws Throwable;

    UUID create(T t);

    void update(T t);

    void delete(UUID uuid);
}
