package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.UserRepositoryImpl;
import com.accenture.flowershop.be.business.UsersRepository;
import com.accenture.flowershop.be.entity.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet(name = "LoginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    private UsersRepository usersRepository;
    @Override
    public void init() throws ServletException {
        this.usersRepository = new UserRepositoryImpl();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login,password);
        if(usersRepository.isExist(user)){
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
