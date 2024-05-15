package ru.mts.demofintech.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.mts.demofintech.entity.Breed;

@Repository
public class BreedDao extends AbstractHibernateDao<Breed> {
    public BreedDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        setClazz(Breed.class);
    }
}
