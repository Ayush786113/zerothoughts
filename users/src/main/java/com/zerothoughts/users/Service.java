package com.zerothoughts.users;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service implements Trigger{
    @Autowired
    private Validator validator;
    @Autowired
    private Appwrite appwrite;
    private Object object;
    synchronized public Object createUser(User user) throws Exception {
        if(!validator.validateUser(user)){
            throw new IllegalArgumentException("User does not have all the required fields set!");
        }
        if(!(validator.validateEmail(user.getEmail()) || validator.validatePhone(user.getPhone()))){
            throw new IllegalArgumentException("Invalid Email or Phone!");
        }
        appwrite.createUser(this, user);
        wait();
        return object;
    }

    synchronized public Object getUser(String id) throws Exception{
        appwrite.getUser(this, id);
        wait();
        return object;
    }

    synchronized public Object updateUser(String id, User user) throws Exception {
        if(!(validator.validateEmail(user.getEmail()) || validator.validatePhone(user.getPhone()))){
            throw new IllegalArgumentException("Invalid Email or Phone!");
        }
        appwrite.updateUser(this, id, user);
        wait();
        return object;
    }

    @Override
    synchronized public void transferObject(Object object) {
        this.object = object;
        notify();
    }
}
