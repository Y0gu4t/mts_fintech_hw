package ru.mts.demofintech.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.mts.demofintech.entity.Animal;

@Repository
public class AnimalDao extends AbstractHibernateDao<Animal> {
    public AnimalDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        setClazz(Animal.class);
    }
}
