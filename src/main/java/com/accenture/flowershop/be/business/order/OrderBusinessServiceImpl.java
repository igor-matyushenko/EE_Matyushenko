package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.order.OrderDAO;
import com.accenture.flowershop.be.access.order.OrderDAOImpl;
import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.entity.Order.Basket;
import com.accenture.flowershop.be.entity.Order.Order;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.enums.StatusOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService{

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BasketBusinessService basketBusinessService;

    private static final Logger log = LoggerFactory.getLogger(OrderBusinessServiceImpl.class);

    @Override
    public void addOrder(Order order) {
        orderDAO.addOrder(order);

    }

    @Override
    public Order newOrderCreate(String userLogin) {
        User user = userDAO.findUserByLogin(userLogin);
        List<Basket> basketList = basketBusinessService.getNewBasketByUserId(user.getId());
        BigDecimal totalPriceOfBasket = new BigDecimal(0);
        for(Basket b : basketList){
            totalPriceOfBasket = totalPriceOfBasket.add(b.getTotalPrice());
        }
        Order order = new Order(StatusOrder.CREATED);
        order.setBasketList(basketList);
        order.setUser(user);
        order.setTotalPrice(totalPriceOfBasket);
        addOrder(order);
        return order;
    }

    @Override
    public Order getOrderById(Long idOrder) {
        return orderDAO.getOrderById(idOrder);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderDAO.getAllOrder();
    }

    @Override
    public List<Order> getOrderByUserID(Long idUser) {
        return orderDAO.getOrderByUserID(idUser);
    }

    @Override
    public void editOrder(Order order) {
        orderDAO.editOrder(order);
    }


}
