package com.auction.repository.impl;

import com.auction.model.entity.Lot;
import com.auction.repository.LotRepositoryCustom;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Helga on 16.11.2017.
 */
@Component
public class LotRepositoryCustomImpl extends AbstractCustomRepositoryImpl<Lot> implements LotRepositoryCustom {
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager em;

    public Session getHibernateSession() {
        return em.unwrap(Session.class);
    }

    @Transactional
    public List<Lot> getLastLots(int count) {
        final Session session = getHibernateSession();
        return session.createCriteria(Lot.class).setMaxResults(count).list();
    }
}
