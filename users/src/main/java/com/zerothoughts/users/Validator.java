package com.zerothoughts.users;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
@Component
public class Validator {
    public boolean validateUser(User user){
        return ( user.getName() != null && user.getPassword() != null && (user.getEmail() != null || user.getPhone() != null) );
    }
    public boolean validateEmail(String email){
        if(email == null)
            return true;
        String regex = "\\w+(\\.\\w)?@\\w+\\.\\w+(\\.\\w+)?";
        return Pattern.matches(regex, email);
    }
    public boolean validatePhone(String phone){
        if(phone == null)
            return true;
        String regex = "\\+\\d{2,3}\\d{10}";
        return Pattern.matches(regex, phone);
    }
}
