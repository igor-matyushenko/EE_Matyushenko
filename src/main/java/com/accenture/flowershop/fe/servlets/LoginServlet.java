package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.UserBusinessServiceImpl;
import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Autowired
    private UserBusinessService userBusinessService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
//    private UserBusinessService userBusinessService;
//    @Override
//    public void init() throws ServletException {
//        this.userBusinessService = new UserBusinessServiceImpl();
//
//    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login,password);
        if(userBusinessService.isExist(user)){
//            HttpSession session = request.getSession(); // создание ссесии
//            session.setAttribute("user", login); // авторизовали сессию
            request.getRequestDispatcher("/userInfo.jsp").forward(request,response);
        } else {
            request.setAttribute("error","Invalid login or password!\n"+
                    "Please check");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
//            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }

    }

}
