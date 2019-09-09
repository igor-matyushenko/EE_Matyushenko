package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.access.FakeStorage;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.stereotype.Service;

//import java.util.logging.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


@Service
public class UserBusinessServiceImpl implements UserBusinessService {
    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessService.class);
//    private static final Logger LOG = Logger.getLogger(UserBusinessService.class.getName());
    private List<User> userList;


    @Override
    public List<User> finAll() {
        return FakeStorage.storage().users();
    }

    @Override
    public void save(User user) {
        FakeStorage.storage().users().add(user);
    }

    @Override
    public boolean isExist(User user) {
        for (User u: FakeStorage.storage().users()) {
            if(user.equals(u)){
                LOG.debug("User: {} successfully sign in"+ u.getLogin());
//                LOG.info("User: login:passowrd  "+user.getLogin()+":"+ user.getPassword()+" successfully sign in");

                return true;
            }
        }
        LOG.debug("User: {}:{} not find in system", user.getLogin(),user.getPassword());
//        LOG.info("User: login:passowrd  "+user.getLogin()+":"+ user.getPassword()+" not find in system" );
        return false;
    }

    @Override
    public boolean createNewUser(String login, String password) {
        User user = new User(login, password);
        for (User u: FakeStorage.storage().users()) {
            if(u.getLogin().toLowerCase().equals(login.toLowerCase())){
                LOG.debug("User: {}:{} is exists",user.getLogin(),user.getPassword());
//                LOG.info("User: login:passowrd  "+user.getLogin()+":"+ user.getPassword()+"   is exists");
                return false;
            }
        }
        save(user);
        LOG.debug("User: {}:{} is register",user.getLogin(),user.getPassword());
//        LOG.info("User: login:passowrd  "+user.getLogin()+":"+ user.getPassword()+"  is register");
        return true;
    }


}
