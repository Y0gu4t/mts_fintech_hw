package ru.mts.demofintech.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public abstract class AbstractHibernateDao<T> {
    private Class<T> clazz;
    protected final SessionFactory sessionFactory;

    public final void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet, null);
    }

    public T findOne(final long id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> findAll() {
        Session session = getCurrentSession();
        session.beginTransaction();
        List<T> list = session.createQuery("from " + clazz.getName(), clazz).list();
        session.close();
        return list;
    }

    public T create(final T entity) {
        Preconditions.checkNotNull(entity, null);
        Transaction transaction = getCurrentSession().beginTransaction();
        getCurrentSession().persist(entity);
        transaction.commit();
        return entity;
    }

    public T update(final T entity) {
        Preconditions.checkNotNull(entity, null);
        Session currentSession = getCurrentSession();
        currentSession.beginTransaction();
        T merge = currentSession.merge(entity);
        currentSession.close();
        return (T) merge;
    }

    public void delete(final T entity) {
        Preconditions.checkNotNull(entity, null);
        getCurrentSession().delete(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkNotNull(entity, null);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
