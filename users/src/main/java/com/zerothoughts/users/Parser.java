package com.zerothoughts.users;

import io.appwrite.models.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class Parser {
    public Object parseUser(io.appwrite.models.User<Map<String, Object>> response){
        User user = new User();
        user.setId(response.getId());
        user.setName(response.getName());
        user.setPhone(nullCheck(response.getPhone()));
        user.setEmail(nullCheck(response.getEmail()));
        return user;
    }

    public Object parseUser(Document<Map<String, Object>> response){
        User user = new User();
        user.setId(response.getId());
        user.setName(String.valueOf(response.getData().get("name")));
        user.setEmail(nullCheck(String.valueOf(response.getData().get("email"))));
        user.setPhone(nullCheck(String.valueOf(response.getData().get("phone"))));
        user.setChildren((ArrayList<String>) response.getData().get("children"));
        return user;
    }

    private String nullCheck(String data){
        if(data.length() == 0 || data.equalsIgnoreCase("null"))
            return null;
        return data;
    }
}
