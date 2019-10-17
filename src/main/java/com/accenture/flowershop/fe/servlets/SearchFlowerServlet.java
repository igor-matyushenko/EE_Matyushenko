package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.ObjectMapperUtils;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet( name = "searchFlowerServlet",urlPatterns = "/searchFlowerServlet")
public class SearchFlowerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private ObjectMapperUtils mapperUtils;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String flowerName = request.getParameter("flowerName");
            String flowerMin = request.getParameter("flowerMinPrice");
            String flowerMax = request.getParameter("flowerMaxPrice");
            if(flowerMin.equals("")) flowerMin = "0";
            if(flowerMax.equals("")) {
                long max = Long.MAX_VALUE;
                flowerMax = ""+ max;
            }
            BigDecimal flowerMinPrice =  BigDecimal.valueOf(Long.parseLong(flowerMin));
            BigDecimal flowerMaxPrice =  BigDecimal.valueOf(Long.parseLong(flowerMax));
            UserDTO user = ((UserDTO) session.getAttribute("user"));
            session.setAttribute("flowerName", flowerName);
            session.setAttribute("flowerMinPrice", flowerMinPrice);
            session.setAttribute("flowerMaxPrice", flowerMaxPrice);
            session.setAttribute("user", user);
            session.setAttribute("flowers", mapperUtils.mapList(flowerBusinessService.getAllFlowersBySearchQueryDsl(flowerName,flowerMinPrice,flowerMaxPrice), FlowerDTO.class));
            request.getRequestDispatcher("/WEB-INF/lib/userPage.jsp").forward(request, resp);
        } else{
            request.getRequestDispatcher("/loginServlet").forward(request, resp);
        }
    }
}
