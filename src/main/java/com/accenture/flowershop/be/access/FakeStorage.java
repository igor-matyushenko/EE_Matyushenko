package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeStorage {
    private static final FakeStorage storage;
    static {
        storage = new FakeStorage();
    }
    private List<User> users;
    private FakeStorage() {
        this.users = new ArrayList<>();
        User user1 = new User("admin","admin123");
        User user2 = new User("user","user123");
        User user3 = new User("igor","qwerty007");
        users.add(user1);
        users.add(user2);
        users.add(user3);

    }
    public static FakeStorage  storage(){
        return storage;
    }
    public List<User> users(){
        return users;
    }
}
