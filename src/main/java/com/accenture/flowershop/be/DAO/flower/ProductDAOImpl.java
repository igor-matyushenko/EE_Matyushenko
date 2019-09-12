package com.accenture.flowershop.be.DAO.flower;

import com.accenture.flowershop.be.entity.flower.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class ProductDAOImpl implements ProductDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(ProductDAOImpl.class);


    @Override
    @Transactional
    public Product findFlowerById(Long id) {
        LOG.debug("findFlowerById "+ id);
        return em.find(Product.class, id);
    }

    @Override
    @Transactional
    public List<Product> getFlowerList() {
        LOG.debug("getFlowerList");
        return em.createQuery("select u from Flower u", Product.class).getResultList();
    }

    @Override
    @Transactional
    public void addFlower(Product flower) {
        LOG.debug("addFlower");
        em.persist(flower);
        em.flush();
    }

    @Override
    @Transactional
    public void deleteFlower(Product flower) {
        LOG.debug("deleteFlower");
        em.remove(flower);
        em.flush();
    }

    @Override
    @Transactional
    public void updateFlower(Product flower) {
        LOG.debug("updateFlower");
        em.merge(flower);
        em.flush();
    }



}
