package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.order.OrderPositionDAO;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.Order.Order;
import com.accenture.flowershop.be.entity.Order.OrderPosition;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.StatusOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
public class OrderPositionBusinessServiceImpl implements OrderPositionBusinessService {

    @Autowired
    private OrderPositionDAO orderPositionDAO;

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private OrderBusinessService orderBusinessService;


    private static final Logger LOG = LoggerFactory.getLogger(OrderPositionBusinessServiceImpl.class);
    private BigDecimal totalSumBasket;

    public BigDecimal getTotalSumBasket() {
        return totalSumBasket;
    }


    public void setTotalSumBasket(BigDecimal totalSumBasket) {
        this.totalSumBasket = totalSumBasket;
    }

    @Override
    @Transactional
    public List<OrderPosition> getActualBasketByUserId(Long userId) {
        Order order = orderBusinessService.getOrderByIdActualBasket(userId);
        return orderPositionDAO.getActualOrderPositionByUserId(userId, order.getId());
    }


    @Override
    @Transactional
    public boolean addOrderPositionToBasket(Long userId, Long flowerID, Long quantityFromOrderPosition, Long quantityFlower) {
        if (quantityFromOrderPosition <= 0 || quantityFromOrderPosition > quantityFlower) {
            return false;
        }
        User user = userBusinessService.findUserById(userId);
        Order newOrderUser = null;
        if(orderBusinessService.getOrderByIdActualBasket(user.getId()) == null) {
            totalSumBasket = BigDecimal.ZERO;
            newOrderUser = new Order(StatusOrder.BASKET);
            user.addOrder(newOrderUser);
            orderBusinessService.addOrder(newOrderUser);
        } else {
            newOrderUser = orderBusinessService.getOrderByIdActualBasket(user.getId());
        }
        Flower flower = flowerBusinessService.getFlowerById(flowerID);
        BigDecimal sumOrderPosition = sumOrderPosition( flowerID,  quantityFromOrderPosition, userBusinessService.getDiscountOfUser(user));
        OrderPosition orderPosition = new OrderPosition(newOrderUser.getId(),flowerID,user.getId(),flower.getTitleFlower(), quantityFromOrderPosition,sumOrderPosition);
        checkIsExistFromBasket(newOrderUser,orderPosition,flower.getTitleFlower(),quantityFromOrderPosition,flower.getPriceFlower());
        return true;
    }


    private void checkIsExistFromBasket(Order order, OrderPosition orderPosition, String flowerName, Long quantity, BigDecimal flowerPrice){
        String check = null;
        for(OrderPosition o : order.getBasketOrder()){
            if(o.getFlowerName().equals(flowerName)){
                int i = order.getBasketOrder().indexOf(o);
                o.setQuantity(o.getQuantity()+quantity);
                o.setTotalPrice(flowerPrice.multiply(BigDecimal.valueOf(o.getQuantity())));
                order.getBasketOrder().set(i,o);
                check = flowerName;
            }
        }
        if(orderPosition !=null && check == null){
            order.getBasketOrder().add(orderPosition);
        }
        setTotalSumBasket(BigDecimal.ZERO);
        for(OrderPosition o : order.getBasketOrder()){
            setTotalSumBasket(getTotalSumBasket().add(o.getTotalPrice()));
        }
        order.setTotalPrice(getTotalSumBasket());
    }

    private BigDecimal sumOrderPosition(Long flowerId, Long quantityFromOrderPosition, Double userDiscount){
        Flower flower = flowerBusinessService.getFlowerById(flowerId);
        BigDecimal flowerPrice = flower.getPriceFlower().multiply(BigDecimal.valueOf(quantityFromOrderPosition));
        BigDecimal sumDiscount = flowerPrice.multiply(BigDecimal.valueOf(userDiscount));
        flowerBusinessService.changeQuantityFlower(flower.getId(),quantityFromOrderPosition);
        return flowerPrice.subtract(sumDiscount);
    }
}
