package com.accenture.flowershop.fe.ws.rest;


import com.accenture.flowershop.be.business.ObjectMapperUtils;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.dto.UserListDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@Component
@Path("/verify")
public class VerificationUserLoginService {

    @Autowired
    private UserBusinessService userBusinessService;
    


    }
//    @POST
//    @Consumes("application/json")
//    public String setPerson(User user) {
//        return user.toString();
//    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/{login}")
//    public Boolean verify(@PathParam("login") String login){
//        if(userBusinessService.findUserByLogin(login) != null) {
//            return true;
//        }
//        return false;
//    }


//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/promoAction?login={login}&balance={balance}")
//    public User addPromotion(@PathParam("login") String login, @PathParam("balance")BigDecimal balance){
//        User user = userBusinessService.findUserByLogin(login);
//        if(user != null){
//            user.setBalance(user.getBalance().add(balance));
//        }
//        return user;
//    }


    //    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/{login}")
//    public Boolean verify(@PathParam("login") String login){
//        if(userBusinessService.findUserByLogin(login) != null) {
//            return true;
//        }
//        return false;
//    }
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("{login}")
//    public UserListDTO verify(@PathParam("login") String login) {
//        User user = userBusinessService.findUserByLogin(login);
//        BigDecimal sum = new BigDecimal(999);
//        if(user != null) {
//            user.setBalance(user.getBalance().add(sum));
//            return mapper.map(user,UserListDTO.class);
//        }
//        return null;
//    }
//
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    public String addPromotion(UserListDTO user) {
//        User newUser = userBusinessService.findUserById(user.getId());
//        BigDecimal sum = new BigDecimal("999");
//        newUser.setBalance(newUser.getBalance().add(sum));
//        Client client =  ClientBuilder.newClient();
//        WebTarget target =  client.target( "http://localhost:8080/flowershop/rest/verify/"+user.getLogin());
//        String response =  target.request( MediaType.APPLICATION_JSON)
//                .accept(MediaType.TEXT_PLAIN_TYPE)
//                .post( Entity.json(newUser),  String.class);
//        userBusinessService.updateUser(newUser);
//        return response;
//    }




//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/addProm")
//    public User addPromotion(User user) {
//        BigDecimal bigDecimal = new BigDecimal(999);
//        if (user != null) {
//            user.setBalance(user.getBalance().add(bigDecimal));
//        }
//        return user;
//    }
//
//    @POST
//    @Consumes("application/json")
//    @Path("/jsonUser")
//    public String setPerson(User user) {
//        return user.toString();
//    }



