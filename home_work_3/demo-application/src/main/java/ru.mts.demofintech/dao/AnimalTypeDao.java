package ru.mts.demofintech.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.mts.demofintech.entity.AnimalType;

@Repository
public class AnimalTypeDao extends AbstractHibernateDao<AnimalType>{
    public AnimalTypeDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        setClazz(AnimalType.class);
    }
}
