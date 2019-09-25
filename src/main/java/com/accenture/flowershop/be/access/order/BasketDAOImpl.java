package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.access.user.UserDAOImpl;
import com.accenture.flowershop.be.entity.Order.Basket;
import com.accenture.flowershop.be.entity.user.User;
import jdk.jfr.Percentage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BasketDAOImpl implements BasketDAO{

    @PersistenceContext
    private EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(BasketDAOImpl.class);

    @Override
    public List<Basket> getBasketByUserId(Long idUser) {
        try {
            TypedQuery<Basket> query =
                    em.createQuery("from Basket e where e.orderID IS NULL AND e.userID =:idUser", Basket.class);
            query.setParameter("idUser", idUser);
            log.debug("getOrderByUserID: " + idUser);
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            log.debug("getOrderByUserID: " + idUser + " не найден");
            return null;
        }
    }
    @Override
    public List<Basket> getNewBasketByUserId(Long idUser) {
        try {
            TypedQuery<Basket> query =
                    em.createQuery("from Basket e where e.orderID IS NULL and e.userID =:idUser", Basket.class);
            query.setParameter("idUser", idUser);
            log.debug("getOrderByUserID: " + idUser);
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            log.debug("getOrderByUserID: " + idUser + " не найден");
            return null;
        }
    }

    @Override
    public Basket getBasketByFlowerName(String flowerName) {
        try {
            TypedQuery<Basket> query =
                    em.createQuery("from Basket e where e.orderID IS NULL and e.flowerName =:flowerName", Basket.class);
            query.setParameter("flowerName", flowerName);
            log.debug("getOrderByUserID: " + flowerName);
            return query.getSingleResult();
        } catch (NoResultException e) {
//            e.printStackTrace();
            log.debug("getBasket: " + flowerName + " не найден");
            return null;
        }
    }

    @Override
    public Boolean addBasket(Basket basket) {
            log.debug("addBasket:  " + basket.toString());
            em.persist(basket);
            em.flush();
        return true;
    }

    @Override
    public List<Basket> getBasketByOrderId(Long orderID) {
        try {
            TypedQuery<Basket> query =
                    em.createQuery("select e from Basket e where e.order_id =:orderID", Basket.class);
            query.setParameter("orderID", orderID);
            log.debug("getBasketByOrderId: " + orderID);
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            log.debug("getOrderByUserID: " + orderID + " не найден");
            return null;
        }
    }

    @Override
    public Boolean updateBasket(Basket basket) {
        em.merge(basket);
        return true;
    }

    @Override
    public void editBasket(List<Basket> basketList) {
        for(Basket basket:basketList){
            em.merge(basket);
        }
    }


}
