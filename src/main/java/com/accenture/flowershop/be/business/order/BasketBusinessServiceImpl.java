package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.order.BasketDAO;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.Order.Basket;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BasketBusinessServiceImpl implements BasketBusinessService{

    @Autowired
    private BasketDAO basketDAO;

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    private UserBusinessService userBusinessService;

    private static final Logger log = LoggerFactory.getLogger(BasketBusinessServiceImpl.class);
    private BigDecimal total = new BigDecimal(0.00);


    @Override
    public List<Basket> getBasketByOrderId(Long orderID) {
        return basketDAO.getBasketByOrderId(orderID);
    }

    @Override
    public void editBasket(List<Basket> basketList) {
        basketDAO.editBasket(basketList);
    }




    @Override
    public List<Basket> getBasketByUserId(Long idUser) {
        return basketDAO.getBasketByUserId(idUser);
    }
    @Override
    public List<Basket> getNewBasketByUserId(Long idUser) {
        return basketDAO.getNewBasketByUserId(idUser);
    }

    @Override
    public Boolean addBasket(String userLogin, Long flowerID, Long quantityToBasket, Long quantityFlower) {
        if(quantityToBasket<=0 || quantityToBasket>quantityFlower){
            return false;
        }
        User user = userBusinessService.findUserByLogin(userLogin);
        Flower flower=flowerBusinessService.getFlowerById(flowerID);
        BigDecimal sumWithDiscountUser = userBusinessService.userSumDiscount(flower.getPriceFlower(),user.getDiscount(),quantityToBasket);
        Basket basket = new Basket(user.getId(), flower.getId(), flower.getTitleFlower(), quantityToBasket, sumWithDiscountUser);
        return addTOBasketWithCheck(basket,flower);
    }

    private boolean addTOBasketWithCheck(Basket basket, Flower flower){
        Basket basket1 = basketDAO.getBasketByFlowerName(basket.getFlowerName());
        if(basket1==null){
            total = total.add(basket.getTotalPrice());
            basketDAO.addBasket(basket);
            changeQuantityFlower(basket,flower);
            return true;
        }
        basket1.setTotalPrice(basket1.getTotalPrice().add(basket.getTotalPrice()));
        basket1.setQuantity(basket1.getQuantity()+basket.getQuantity());
        basketDAO.updateBasket(basket1);
        total = total.add(basket1.getTotalPrice());
        changeQuantityFlower(basket1,flower);
        return true;
    }

    private void changeQuantityFlower(Basket basket,Flower flower){
        flower.setQuantity(flower.getQuantity()-basket.getQuantity());
        flowerBusinessService.editFlower(flower);
    }
    @Override
    public BigDecimal getTotal() {

        return total.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    private void setTotal(BigDecimal total) {
        this.total = total;
    }
}
