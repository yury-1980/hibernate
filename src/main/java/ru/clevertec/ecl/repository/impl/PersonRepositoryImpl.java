package ru.clevertec.ecl.repository.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly = true)
    public List<Person> findByAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Person p", Person.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findByUUID(UUID uuid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT p FROM Person p WHERE p.uuid = :uuid");
        query.setParameter("uuid", uuid);
        Person person = (Person) query.uniqueResult();

        return Optional.ofNullable(person);
    }

    @Override
    @Transactional
    public UUID create(Person person) {
        sessionFactory.getCurrentSession()
                .persist(person);
        return person.getUuid();
    }

    @Override
    @Transactional
    public void update(Person person) {

    }

    @Override
    @Transactional
    public void delete(UUID uuid) {

    }
}