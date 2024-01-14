package ru.clevertec.ecl.repository.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.entity.House;
import ru.clevertec.ecl.repository.HouseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class HouseRepositoryImpl implements HouseRepository {

    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<House> findByAll() {

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select h from House h", House.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<House> findByUUID(UUID uuid) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT h FROM House h WHERE h.uuid = :uuid");
        query.setParameter("uuid", uuid);
        House house = (House) query.uniqueResult();

        return Optional.ofNullable(house);
    }

    @Override
    @Transactional
    public UUID create(House house) {

        sessionFactory.getCurrentSession()
                .persist(house);

        return house.getUuid();
    }

    @Override
    @Transactional
    public void update(House t) {

    }

    @Override
    @Transactional
    public void delete(UUID uuid) {

    }
}
