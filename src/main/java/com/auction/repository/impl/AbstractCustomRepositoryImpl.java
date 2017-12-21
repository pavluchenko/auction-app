package com.auction.repository.impl;

import com.auction.model.entity.BaseEntity;
import com.auction.repository.AbstractCustomRepository;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Helga on 11.12.2017.
 */
public abstract class AbstractCustomRepositoryImpl<T extends BaseEntity> implements AbstractCustomRepository<T> {
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager em;

    public Session getHibernateSession() {
        return em.unwrap(Session.class);
    }

    @Transactional
    public List<T> getByPage(T t, int pageid, int total) {
        final Session session = getHibernateSession();
        // get all count // посчитать сколько отобразить
        return (List<T>) session.createCriteria(t.getClass()).setFirstResult(pageid).setMaxResults(total).list();
    }
}
