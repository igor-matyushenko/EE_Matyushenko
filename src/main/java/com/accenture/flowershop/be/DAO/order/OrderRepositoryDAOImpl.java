package com.accenture.flowershop.be.DAO.order;

import com.accenture.flowershop.be.entity.order.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class OrderRepositoryDAOImpl implements OrderRepositoryDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(OrderRepositoryDAOImpl.class);


    @Override
    @Transactional
    public void saveOrder(OrderRepository order) {
        LOG.debug("saveOrder");
        em.persist(order);
        em.flush();
    }

    @Override
    @Transactional
    public void deleteOrder(OrderRepository order) {
        LOG.debug("deleteOrder");
        em.remove(order);
        em.flush();
    }

    @Override
    @Transactional
    public void updateOrder(OrderRepository order) {
        LOG.debug("updateOrder");
        em.merge(order);
        em.flush();
    }

    @Override
    @Transactional
    public OrderRepository findOrder(Long orderId) {
        LOG.debug("findOrder "+ orderId);
        return em.find(OrderRepository.class, orderId);
    }

    @Override
    @Transactional
    public List<OrderRepository> getAllMyOrders(Long idUser) {
        TypedQuery<OrderRepository> query = em.createQuery("select o from orderRep as o where o.user.id = :id", OrderRepository.class);
        query.setParameter("id", idUser);
        return query.getResultList();
    }

    @Override
    public List<OrderRepository> findAllOrders() {
        LOG.debug("findAllOrders");
        try {
            TypedQuery<OrderRepository> query;
            query = em.createQuery("select o from orderRep o", OrderRepository.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
