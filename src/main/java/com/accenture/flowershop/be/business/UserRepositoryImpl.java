package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.access.FakeStorage;
import com.accenture.flowershop.be.entity.user.User;

import java.util.List;

public class UserRepositoryImpl implements UsersRepository {

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
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean createNewUser(String login, String password) {
        User user = new User(login, password);
        for (User u: FakeStorage.storage().users()) {
            if(u.getLogin().toLowerCase().equals(login.toLowerCase())){
                return false;
            }
        }
        save(user);
        return true;
    }


}
