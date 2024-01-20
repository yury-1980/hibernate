package ru.clevertec.ecl.repository.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.clevertec.ecl.entity.House;
import ru.clevertec.ecl.entity.Person;
import ru.clevertec.ecl.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private SessionFactory sessionFactory;

    @Override
    public List<Person> findByAll(int pageNumber, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery("SELECT p FROM Person p LEFT JOIN FETCH p.houseList", Person.class);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Person> persons = query.getResultList();
        persons.forEach(person -> person
                .setHouse(session.createQuery("SELECT h FROM House h LEFT JOIN FETCH h.residentsList r WHERE r.uuid = :personUUID", House.class)
                        .setParameter("personUUID", person.getUuid())
                        .uniqueResult()));
        return persons;
    }

    @Override
    public Optional<Person> findByUUID(UUID uuid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT p FROM Person p WHERE p.uuid = :uuid");
        query.setParameter("uuid", uuid);
        Person person = (Person) query.uniqueResult();

        return Optional.ofNullable(person);
    }

    @Override
    public UUID create(Person person) {
        sessionFactory.getCurrentSession()
                .persist(person);
        return person.getUuid();
    }

    @Override
    public void update(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(person);
    }

    @Override
    public void delete(UUID uuid) {
        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createNativeQuery("DELETE FROM person p WHERE p.uuid = :uuid", Person.class);
        query.setParameter("uuid", uuid);
    }
}
