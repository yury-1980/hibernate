package ru.clevertec.ecl.repository.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.entity.House;
import ru.clevertec.ecl.entity.Person;
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
    public List<House> findByAll(int pageNumber, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        Query<House> query = session.createQuery("SELECT h FROM House h LEFT JOIN FETCH h.ownerList", House.class);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<House> houses = query.getResultList();
        houses.forEach(house -> house.setResidentsList(session.createQuery("SELECT p FROM Person p LEFT JOIN FETCH p.house h WHERE h.uuid = :houseUUID", Person.class)
                .setParameter("houseUUID", house.getUuid())
                .getResultList()));
        return houses;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<House> findByUUID(UUID uuid) {

        Session session = sessionFactory.getCurrentSession();
        Query<House> query = session.createQuery("SELECT h FROM House h WHERE h.uuid = :uuid", House.class)
                .setParameter("uuid", uuid);
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
    public void update(House house) {
        Session session = sessionFactory.getCurrentSession();
        House merge = session.merge(house);

    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        Session session = sessionFactory.getCurrentSession();
        Query<House> query = session.createNativeQuery("DELETE FROM House h WHERE h.uuid = :uuid", House.class);
        query.setParameter("uuid", uuid);
    }
}
